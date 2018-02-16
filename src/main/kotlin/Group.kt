package red.ael.jex

internal data class Group(private val regexp: RegexBuilder) : ElementaryRegexBuilder {
    override fun append(builder: StringBuilder) {
        builder.append("(").add(regexp).append(")")
    }

    override fun toString(): String {
        return regexString()
    }
}