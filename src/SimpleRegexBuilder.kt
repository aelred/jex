package red.ael.jex

/**
 * A builder for a [simple regular expression](http://www.cs.sfu.ca/~cameron/Teaching/384/99-3/regexp-plg.html).
 */
internal abstract class SimpleRegexBuilder : RegexBuilder() {
    final override fun toSimpleRegexBuilder() = this
}