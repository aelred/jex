package red.ael.jex

internal data class OneOrMore(private val regexp: ElementaryRegexBuilder) : BasicRegexBuilder() {
    override fun append(builder: StringBuilder) {
        builder.add(regexp).append("+")
    }

    // Optimisation because this is a no-op
    override fun oneOrMore() = this
}
