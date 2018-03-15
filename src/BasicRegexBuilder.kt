package red.ael.jex

/**
 * A builder for a [basic regular expression](http://www.cs.sfu.ca/~cameron/Teaching/384/99-3/regexp-plg.html).
 */
internal abstract class BasicRegexBuilder : SimpleRegexBuilder() {
    final override fun toBasicRegexBuilder() = this
}