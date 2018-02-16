package red.ael.jex

internal data class ZeroOrMore(private val regexp: ElementaryRegexBuilder) : BasicRegexBuilder {
    override fun append(builder: StringBuilder) {
        builder.add(regexp).append("*")
    }

    override fun toString(): String {
        return regexString()
    }

    override fun zeroOrMore(): RegexBuilder {
        // Optimisation because this is a no-op
        return this
    }

    override fun oneOrMore(): RegexBuilder {
        // Optimisation because this is a no-op
        return this
    }
}