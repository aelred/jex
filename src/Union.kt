package red.ael.jex

internal data class Union(private val regexps: List<SimpleRegexBuilder>) : RegexBuilder() {
    override fun append(builder: StringBuilder) {
        var firstElem = true
        for (regexp in regexps) {
            if (!firstElem) {
                builder.append("|")
            } else {
                firstElem = false
            }
            builder.add(regexp)
        }
    }
}