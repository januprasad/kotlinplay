package com.github.kotlin_tryout.builderpatterns

interface Builder<T> {
    fun setProperty1(value: String): Builder<T>

    fun setProperty2(value: Int): Builder<T>

    fun build(): T
}

interface Fruit {
    var property1: String
    var property2: Int
}

class Pear(
    override var property1: String,
    override var property2: Int,
) : Fruit

class FruitBuilder : Builder<Fruit> {
    lateinit var fruit: Fruit

    inline fun <reified T : Fruit> instance(): Builder<Fruit> {
        fruit = T::class.java.getDeclaredConstructor().newInstance()
        return this
    }

    override fun setProperty1(value: String): Builder<Fruit> {
        fruit.property1 = value
        return this
    }

    override fun setProperty2(value: Int): Builder<Fruit> {
        fruit.property2 = value
        return this
    }

    override fun build(): Fruit = fruit
}

fun main() {
    val pear =
        FruitBuilder()
            .instance<Fruit>()
            .setProperty1("Green")
            .setProperty2(10)
            .build() as Pear

    println("${pear.property1}, ${pear.property2}") // Green, 10
}
