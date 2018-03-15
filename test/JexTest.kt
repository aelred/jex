package red.ael.jex

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class JexTest : StringSpec() {
    init {
        "any() is /./" {
            any().toString() shouldBe "."
        }

        "lit('abc') is /abc/" {
            lit("abc").toString() shouldBe "abc"
        }

        "concat(lit('abc'), lit('xyz')) is /abcxyz/" {
            concat(lit("abc"), lit("xyz")).toString() shouldBe "abcxyz"
        }

        "lit('abc').zeroOrMore() is /(abc)*/" {
            lit("abc").zeroOrMore().toString() shouldBe "(abc)*"
        }

        "lit('.') should escape special character" {
            lit(".").toString() shouldBe "\\."
        }

        "union(lit('a'), lit('b')) is /a|b/" {
            union(lit("a"), lit("b")).toString() shouldBe "a|b"
        }

        "lit('x').zeroOrMore() is /x*/" {
            lit("x").zeroOrMore().zeroOrMore().toString() shouldBe "x*"
        }

        "lit('x').zeroOrMore().zeroOrMore() is /x*/" {
            lit("x").zeroOrMore().zeroOrMore() shouldBe lit("x").zeroOrMore()
        }
    }
}