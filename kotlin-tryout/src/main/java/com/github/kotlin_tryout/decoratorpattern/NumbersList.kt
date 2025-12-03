package com.github.kotlin_tryout.decoratorpattern

interface Numbers {
    fun generate(): List<Int>
}

class BaseNumbers(
    private val items: MutableList<Int>,
) : Numbers {
    override fun generate(): List<Int> = items
}

abstract class NumberDecorator(
    protected val numbers: Numbers,
) : Numbers {
    override fun generate(): List<Int> = numbers.generate()
}

class FactorsFive(
    numbers: Numbers,
) : NumberDecorator(numbers) {
    override fun generate(): List<Int> =
        numbers.generate().filter {
            it % 5 == 0
        }
}

class FactorsThree(
    numbers: Numbers,
) : NumberDecorator(numbers) {
    override fun generate(): List<Int> =
        numbers.generate().filter {
            it % 3 == 0
        }
}

fun main() {
    val items = (1..100).toMutableList()
    val baseNumber = BaseNumbers(items)
    val factorsFive = FactorsFive(baseNumber)
    val factorsThreeAndFive = FactorsThree(factorsFive)
    val factorsThree = FactorsThree(baseNumber)
    println(" OG : " + baseNumber.generate())
    println(" Factors 5 : " + factorsFive.generate())
    println(" Factors 3 from O G : " + factorsThree.generate())
    println(" Factors 3 & 5 (from the last out such as factors of 5) : " + factorsThreeAndFive.generate())
}
