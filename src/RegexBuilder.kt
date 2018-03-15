package red.ael.jex

/**
 * A builder for a regular expression.
 */
abstract class RegexBuilder {

    /**
     * Serializes the [RegexBuilder] into a string representing a valid regular expression.
     */
    final override fun toString(): String {
        val builder = StringBuilder()
        append(builder)
        return builder.toString()
    }

    /**
     * @see concat
     */
    open infix fun and(regex: RegexBuilder) = concat(this, regex)

    /**
     * @see concat
     */
    infix fun and(string: String) = and(lit(string))

    /**
     * @see union
     */
    infix fun or(regex: RegexBuilder) = union(this, regex)

    /**
     * @see union
     */
    infix fun or(string: String) = or(lit(string))

    /**
     * @see concat
     */
    operator fun times(regexp: RegexBuilder) = and(regexp)

    /**
     * @see concat
     */
    operator fun times(string: String) = and(lit(string))

    /**
     * @see union
     */
    operator fun plus(regexp: RegexBuilder) = or(regexp)

    /**
     * @see union
     */
    operator fun plus(string: String) = or(lit(string))

    /**
     * A [RegexBuilder] that matches this [RegexBuilder] zero or more times - the Kleene star "*"
     */
    open fun zeroOrMore(): RegexBuilder = ZeroOrMore(toElementaryRegexBuilder())

    /**
     * A [RegexBuilder] that matches this [RegexBuilder] one or more times - the plus "+"
     *
     * @sample red.ael.jex.JexSamples.oneOrMoreSample
     */
    open fun oneOrMore(): RegexBuilder = OneOrMore(toElementaryRegexBuilder())

    internal open fun toBasicRegexBuilder(): BasicRegexBuilder = Group(this)

    internal open fun toSimpleRegexBuilder(): SimpleRegexBuilder = Group(this)

    internal open fun toElementaryRegexBuilder(): ElementaryRegexBuilder = Group(this)

    internal abstract fun append(builder: StringBuilder)
}

