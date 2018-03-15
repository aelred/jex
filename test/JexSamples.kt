package red.ael.jex

import io.kotlintest.matchers.shouldEqual
import junit.framework.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class JexSamples {

    @Test fun anySample() {
        assertTrue(any() matches "a")
        assertTrue(any() matches "💩")
        assertFalse(any() matches "")
        assertFalse(any() matches "aa")
    }

    @Test fun concatSample() {
        val regex1 = concat("a".zeroOrMore(), lit("b"), "c".zeroOrMore())
        val regex2 = "a".zeroOrMore() and "b" and "c".zeroOrMore()
        val regex3 = "a".zeroOrMore() * "b" * "c".zeroOrMore()

        regex1 shouldEqual regex2
        regex1 shouldEqual regex3

        assertTrue(regex1 matches "b")
        assertTrue(regex1 matches "abc")
        assertTrue(regex1 matches "bccc")

        assertFalse(regex1 matches "ac")
    }

    @Test fun unionSample() {
        val regex1 = union(lit("a"), lit("b"))
        val regex2 = "a" or "b"
        val regex3 = lit("a") + lit("b")

        regex1 shouldEqual regex2
        regex1 shouldEqual regex3

        assertTrue(regex1 matches "a")
        assertTrue(regex1 matches "b")

        assertFalse(regex1 matches "ab")
    }

    @Test fun litSample() {
        val bobRegex = lit("bob")
        assertTrue(bobRegex matches "bob")

        val dotRegex = lit(".*")
        assertTrue(dotRegex matches ".*")
        assertFalse(dotRegex matches "bob")
    }

    private infix fun RegexBuilder.matches(string: String): Boolean {
        return Regex(toString()).matches(string)
    }
}
