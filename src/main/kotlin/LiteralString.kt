package red.ael.jex


internal data class LiteralString(private val string: String) : BasicRegexBuilder {

    override fun append(builder: StringBuilder) {
        builder.append(string.escapeLiteralRegex())
    }

    override fun toString(): String {
        return regexString()
    }
}