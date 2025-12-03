package com.github.kotlin_tryout.builderpatterns

interface Juicer<out T> {
    fun getJuicer(): T
}

interface IFruit {
    fun getFreshJuice(): String
}

class Mango : IFruit {
    override fun getFreshJuice() = "Mango fresh juice"
}

class PhilipsJuicer<out T>(
    private val value: T,
) : Juicer<T> {
    override fun getJuicer(): T = value
}

class JuiceBuilder<T> {
    private var fruit: T? = null
    private var ice: Boolean = false

    fun setFruit(value: T): JuiceBuilder<T> {
        this.fruit = value
        return this
    }

    fun build(): PhilipsJuicer<T> = PhilipsJuicer(fruit!!)

    fun setIce(ice: Boolean): JuiceBuilder<T> {
        this.ice = ice
        return this
    }
}

fun main() {
    val builder = JuiceBuilder<Mango>()
    val mango = Mango()
    val covariantObject = builder.setFruit(mango).setIce(true).build()

    println(covariantObject.getJuicer()) // Output: Hello, world!

    // Covariant type safety:
    val covariantInterface: Juicer<IFruit> = covariantObject
    println(covariantInterface.getJuicer().getFreshJuice()) // Output: Hello, world! (safe downcast)
}
