package com.github.kotlin_tryout.builderpatterns

enum class Crust {
    THIN,
}

enum class Sauce {
    MARINARA,
}

enum class Cheese {
    MOZZARELLA,
}

enum class Topping {
    PEPPERONI,
    ONION,
}

interface PizzaBuilder {
    fun addCrust(crust: Crust): PizzaBuilder

    fun addSauce(sauce: Sauce): PizzaBuilder

    fun addCheese(cheese: Cheese): PizzaBuilder

    fun addToppings(toppings: List<Topping>): PizzaBuilder

    fun build(): Pizza
}

class Pizza(
    private val crust: Crust,
    private val sauce: Sauce,
    private val cheese: Cheese,
    private val toppings: List<Topping>,
)

class PepperoniPizzaBuilder : PizzaBuilder {
    private val pizza = PizzaBuilderImpl()

    override fun addCrust(crust: Crust): PizzaBuilder {
        pizza.addCrust(crust)
        return this
    }

    override fun addSauce(sauce: Sauce): PizzaBuilder {
        pizza.addSauce(sauce)
        return this
    }

    override fun addCheese(cheese: Cheese): PizzaBuilder {
        pizza.addCheese(cheese)
        return this
    }

    override fun addToppings(toppings: List<Topping>): PizzaBuilder {
        pizza.addToppings(toppings)
        return this
    }

    // ... other methods

    override fun build(): Pizza = pizza.build()
}

class PizzaBuilderImpl : PizzaBuilder {
    private var crust: Crust? = null
    private var sauce: Sauce? = null
    private var cheese: Cheese? = null
    private val toppings = mutableListOf<Topping>()

    override fun addCrust(crust: Crust): PizzaBuilder {
        this.crust = crust
        return this
    }

    override fun addSauce(sauce: Sauce): PizzaBuilder {
        this.sauce = sauce
        return this
    }

    override fun addCheese(cheese: Cheese): PizzaBuilder {
        this.cheese = cheese
        return this
    }

    override fun addToppings(toppings: List<Topping>): PizzaBuilder {
        this.toppings.plus(toppings)
        return this
    }

    override fun build(): Pizza = Pizza(crust!!, sauce!!, cheese!!, toppings)
}

fun main() {
    val pepperoniPizza =
        PepperoniPizzaBuilder()
            .addCrust(Crust.THIN)
            .addSauce(Sauce.MARINARA)
            .addCheese(Cheese.MOZZARELLA)
            .addToppings(listOf(Topping.PEPPERONI, Topping.ONION))
            .build()

    println(pepperoniPizza)
}
