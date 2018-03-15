package red.ael.jex.it

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import red.ael.jex.RegexBuilder
import red.ael.jex.any
import red.ael.jex.lit
import red.ael.jex.times

class JexPropertyTest : StringSpec() {

    init {
        "can create a simple regexp" {
            val regexp = (("abc" * any().zeroOrMore()) + "xyz") * "a"

            regexp.toString() shouldBe "(abc.*|xyz)a"
        }

        "can create a simple regexp with special characters" {
            val regexp = lit("abc.*")

            regexp.toString() shouldBe "abc\\.\\*"
        }

        "/x/ matches 'x" {
            forAll({ x: String ->
                lit(x).matches(x)
            })
        }

        "/x/ matches exactly 'x'" {
            forAll({ x: String, y: String ->
                (x == y) == lit(x).matches(y)
            })
        }

        "if /A/ matches X then /A|B/ matches X" {
            forAll(JexGenerator(), JexGenerator(), SimpleString(), { a, b, x ->
                a.matches(x) implies (a + b).matches(x)
            })
        }

        "/A|B/ is equivalent to /B|A/" {
            forAll(JexGenerator(), JexGenerator(), SimpleString(), { a, b, x ->
                (a + b).matches(x) == (b + a).matches(x)
            })
        }

        "if /A|B/ matches X then /A/ matches X or /B/ matches X" {
            forAll(JexGenerator(), JexGenerator(), SimpleString(), { a, b, x ->
                (a + b).matches(x) implies (a.matches(x) || b.matches(x))
            })
        }

        "if /A/ matches X and /B/ matches Y then /AB/ matches XY" {
            forAll(JexGenerator(), JexGenerator(), SimpleString(), SimpleString(), { a, b, x, y ->
                (a.matches(x) && b.matches(y)) implies (a * b).matches(x + y)
            })
        }

        "if /AB/ matches X then there exists YZ=X such that /A/ matches Y and /B/ matches Z" {
            forAll(JexGenerator(), JexGenerator(), SimpleString(), { a, b, x ->
                (a * b).matches(x) implies splitsOf(x).any { (y, z) -> a.matches(y) && b.matches(z) }
            })
        }

        "/A*/ matches an empty string" {
            forAll(JexGenerator(), { a ->
                a.zeroOrMore().matches("")
            })
        }

        "if /A/ matches X then /A*/ matches any number of repetitions of X" {
            forAll(JexGenerator(), SimpleString(), Gen.choose(0, 10), { a, x, n ->
                a.matches(x) implies a.zeroOrMore().matches(x.repeat(n))
            })
        }

        "/A+/ is equivalent to /AA*/" {
            forAll(JexGenerator(), SimpleString(), { a, x ->
                a.oneOrMore().matches(x) == (a * a.zeroOrMore()).matches(x)
            })
        }
    }

    private infix fun Boolean.implies(other: Boolean): Boolean {
        return !this || other
    }

    private fun RegexBuilder.matches(string: String): Boolean {
        return Regex(toString()).matches(string)
    }

    private fun splitsOf(string: String): List<Pair<String, String>> {
        return List(string.length + 1, { string.substring(0, it) to string.substring(it, string.length) })
    }
}
