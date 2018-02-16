package red.ael.jex.it

import io.kotlintest.properties.Gen
import red.ael.jex.*
import java.util.stream.Collectors

class SimpleString : Gen<String> {
    companion object {
        private val VALUES = listOf("x", "y", "z", ".")
    }

    override fun generate(): String {
        return shortList(Gen.oneOf(VALUES)).generate().stream().collect(Collectors.joining())
    }
}

fun <T> shortList(gen: Gen<T>) = object : Gen<List<T>> {
    override fun generate(): List<T> = (0 until Gen.choose(0, 10).generate()).map { gen.generate() }.toList()
}

class JexGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder {
        return Gen.oneOf(LiteralGenerator(), AnyGenerator(), ZeroOrMoreGenerator(), OneOrMoreGenerator()).generate()
    }
}

class LiteralGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder = lit(SimpleString().generate())
}

class AnyGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder = any()
}

class ConcatGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder {
        val regexps = JexGenerator()
        return concat(regexps.generate(), regexps.generate())
    }
}

class UnionGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder {
        val regexps = JexGenerator()
        return union(regexps.generate(), regexps.generate())
    }
}

class ZeroOrMoreGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder {
        return JexGenerator().generate().zeroOrMore()
    }
}

class OneOrMoreGenerator : Gen<RegexBuilder> {
    override fun generate(): RegexBuilder {
        return JexGenerator().generate().oneOrMore()
    }
}
