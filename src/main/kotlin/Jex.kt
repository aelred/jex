@file:JvmName("Jex")

package red.ael.jex

fun any(): RegexBuilder {
    return AnyChar
}

fun concat(regexp1: RegexBuilder, regexp2: RegexBuilder, vararg regexps: RegexBuilder): RegexBuilder {
    return Concat(listOf(regexp1, regexp2, *regexps).map(RegexBuilder::toBasicJex))
}

fun union(regexp1: RegexBuilder, regexp2: RegexBuilder, vararg regexps: RegexBuilder): RegexBuilder {
    return Union(listOf(regexp1, regexp2, *regexps).map(RegexBuilder::toSimpleJex))
}

fun lit(string: String): RegexBuilder {
    return if (string.length == 1) {
        LiteralChar(string[0])
    } else {
        LiteralString(string)
    }
}

infix fun String.and(regex: RegexBuilder): RegexBuilder {
    return concat(lit(this), regex)
}

infix fun String.or(regex: RegexBuilder): RegexBuilder {
    return union(lit(this), regex)
}

operator fun String.times(regex: RegexBuilder): RegexBuilder {
    return concat(lit(this), regex)
}

internal fun StringBuilder.add(regexp: RegexBuilder): StringBuilder {
    regexp.append(this)
    return this
}

private val SPECIAL_CHARS: Regex = Regex("[\\[\\]{}()^\$.|*+?\\\\]")

internal fun String.escapeLiteralRegex(): String {
    return replace(SPECIAL_CHARS, "\\\\$0")
}