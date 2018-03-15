@file:JvmName("Jex")

package red.ael.jex

/**
 * A [RegexBuilder] that matches any single character - the dot '.'
 *
 * @sample red.ael.jex.JexSamples.anySample
 */
fun any(): RegexBuilder = AnyChar

/**
 * Concatenate two or more [RegexBuilder]s into a single [RegexBuilder] that matches any concatenation of strings that
 * match the given [RegexBuilder]s.
 *
 * @see RegexBuilder.and
 * @see RegexBuilder.times
 * @see String.and
 * @see String.times
 *
 * @sample red.ael.jex.JexSamples.concatSample
 */
fun concat(regexp1: RegexBuilder, regexp2: RegexBuilder, vararg regexps: RegexBuilder): RegexBuilder {
    return Concat(listOf(regexp1, regexp2, *regexps).map(RegexBuilder::toBasicRegexBuilder))
}

/**
 * Unions two or more [RegexBuilder]s into a single [RegexBuilder] that matches any string that matches one of the
 * given [RegexBuilder]s.
 *
 * @see RegexBuilder.or
 * @see RegexBuilder.plus
 * @see String.or
 *
 * @sample red.ael.jex.JexSamples.unionSample
 */
fun union(regexp1: RegexBuilder, regexp2: RegexBuilder, vararg regexps: RegexBuilder): RegexBuilder {
    return Union(listOf(regexp1, regexp2, *regexps).map(RegexBuilder::toSimpleRegexBuilder))
}

/**
 * A [RegexBuilder] that matches the given literal string, even if it contains special characters.
 *
 * @sample red.ael.jex.JexSamples.litSample
 */
fun lit(string: String): RegexBuilder {
    return if (string.length == 1) {
        LiteralChar(string[0])
    } else {
        LiteralString(string)
    }
}

/**
 * @see concat
 */
infix fun String.and(regex: RegexBuilder) = lit(this).and(regex)

/**
 * @see union
 */
infix fun String.or(regex: RegexBuilder) = lit(this).or(regex)

/**
 * @see union
 */
infix fun String.or(regex: String) = lit(this).or(regex)

/**
 * @see union
 */
operator fun String.times(regex: RegexBuilder) = lit(this).times(regex)

/**
 * @see RegexBuilder.zeroOrMore
 */
fun String.zeroOrMore() = lit(this).zeroOrMore()

/**
 * @see RegexBuilder.oneOrMore
 */
fun String.oneOrMore() = lit(this).oneOrMore()

internal fun StringBuilder.add(regexp: RegexBuilder): StringBuilder {
    regexp.append(this)
    return this
}

private val SPECIAL_CHARS: Regex = Regex("[\\[\\]{}()^\$.|*+?\\\\]")

internal fun String.escapeLiteralRegex() = replace(SPECIAL_CHARS, "\\\\$0")