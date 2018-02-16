package red.ael.jex

internal data class LiteralChar(private val char: Char) : ElementaryRegexBuilder {

    override fun append(builder: StringBuilder) {
        builder.append(char.toString().escapeLiteralRegex())
    }

    override fun toString(): String {
        return regexString()
    }
}