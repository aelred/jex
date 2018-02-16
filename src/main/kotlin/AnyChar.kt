package red.ael.jex

/**
 *
 */
internal object AnyChar : ElementaryRegexBuilder {
    override fun append(builder: StringBuilder) {
        builder.append(".")
    }

    override fun toString(): String {
        return regexString()
    }
}