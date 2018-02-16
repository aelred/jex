package red.ael.jex

interface BasicRegexBuilder : SimpleRegexBuilder {
    override fun toBasicJex(): BasicRegexBuilder {
        return this
    }
}