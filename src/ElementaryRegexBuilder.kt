package red.ael.jex

/**
 * A builder for an [elementary regular expression](http://www.cs.sfu.ca/~cameron/Teaching/384/99-3/regexp-plg.html).
 */
internal abstract class ElementaryRegexBuilder : BasicRegexBuilder() {
    final override fun toElementaryRegexBuilder() = this
}