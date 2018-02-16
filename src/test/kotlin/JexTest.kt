package rel.ael.jex

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import red.ael.jex.any
import red.ael.jex.concat
import red.ael.jex.lit
import red.ael.jex.union

class JexTest : StringSpec() {
    init {
        "any() is /./" {
            any().regexString() shouldBe "."
        }

        "lit('abc') is /abc/" {
            lit("abc").regexString() shouldBe "abc"
        }

        "concat(lit('abc'), lit('xyz')) is /abcxyz/" {
            concat(lit("abc"), lit("xyz")).regexString() shouldBe "abcxyz"
        }

        "lit('abc').zeroOrMore() is /(abc)*/" {
            lit("abc").zeroOrMore().regexString() shouldBe "(abc)*"
        }

        "lit('.') should escape special character" {
            lit(".").regexString() shouldBe "\\."
        }

        "union(lit('a'), lit('b')) is /a|b/" {
            union(lit("a"), lit("b")).regexString() shouldBe "a|b"
        }

        "lit('x').zeroOrMore() is /x*/" {
            lit("x").zeroOrMore().zeroOrMore().regexString() shouldBe "x*"
        }

        "lit('x').zeroOrMore().zeroOrMore() is /x*/" {
            lit("x").zeroOrMore().zeroOrMore() shouldBe lit("x").zeroOrMore()
        }
    }
}