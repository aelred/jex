package red.ael.jex

internal data class OneOrMore(private val regexp: ElementaryRegexBuilder) : BasicRegexBuilder {
    override fun append(builder: StringBuilder) {
        builder.add(regexp).append("+")
    }

    override fun toString(): String {
        return regexString()
    }

    override fun oneOrMore(): RegexBuilder {
        // Optimisation because this is a no-op
        return this
    }
}
