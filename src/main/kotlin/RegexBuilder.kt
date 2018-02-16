package red.ael.jex

interface RegexBuilder {

    fun append(builder: StringBuilder)

    fun regexString(): String {
        val builder = StringBuilder()
        append(builder)
        return builder.toString()
    }

    infix fun and(regex: RegexBuilder): RegexBuilder {
        return concat(this, regex)
    }

    infix fun and(string: String): RegexBuilder {
        return and(lit(string))
    }

    infix fun or(regex: RegexBuilder): RegexBuilder {
        return union(this, regex)
    }

    infix fun or(string: String): RegexBuilder {
        return or(lit(string))
    }

    operator fun times(regexp: RegexBuilder): RegexBuilder {
        return and(regexp)
    }

    operator fun times(string: String): RegexBuilder {
        return and(lit(string))
    }

    operator fun plus(regexp: RegexBuilder): RegexBuilder {
        return or(regexp)
    }

    operator fun plus(string: String): RegexBuilder {
        return or(lit(string))
    }

    fun zeroOrMore(): RegexBuilder {
        return ZeroOrMore(toElementaryJex())
    }

    fun oneOrMore(): RegexBuilder {
        return OneOrMore(toElementaryJex())
    }

    fun toBasicJex(): BasicRegexBuilder {
        return Group(this)
    }

    fun toSimpleJex(): SimpleRegexBuilder {
        return Group(this)
    }

    fun toElementaryJex(): ElementaryRegexBuilder {
        return Group(this)
    }
}

