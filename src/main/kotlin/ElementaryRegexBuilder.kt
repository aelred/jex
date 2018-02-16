package red.ael.jex

interface ElementaryRegexBuilder : BasicRegexBuilder {
    override fun toElementaryJex(): ElementaryRegexBuilder {
        return this
    }
}