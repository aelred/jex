package red.ael.jex

interface SimpleRegexBuilder : RegexBuilder {
    override fun toSimpleJex(): SimpleRegexBuilder {
        return this
    }
}