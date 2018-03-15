package red.ael.jex

internal data class Concat(private val regexps: List<BasicRegexBuilder>) : SimpleRegexBuilder() {
    override fun append(builder: StringBuilder) {
        for (regexp in regexps) {
            builder.add(regexp)
        }
    }

    override fun and(regex: RegexBuilder) = Concat(regexps.plus(regex.toBasicRegexBuilder()))
}