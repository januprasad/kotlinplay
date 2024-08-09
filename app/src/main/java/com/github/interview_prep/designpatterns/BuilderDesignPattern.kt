package com.github.interview_prep.designpatterns

class Burger private constructor(
    private val meat: Meat,
    private val bread: Bread,
    private val cheese: Cheese,
    private val mayo: Mayonnaise,
) {
    fun serve() {
        val result =
            """
            Burger with ${bread.name} 
            topped ${mayo.name}, 
            and ${cheese.name}
            
            """
        println(result)
    }

    class Builder {
        private lateinit var meat: Meat
        private lateinit var bread: Bread
        private lateinit var cheese: Cheese
        private lateinit var mayo: Mayonnaise

        fun addMeat(meat: Meat) =
            apply {
                this.meat = meat
            }

        fun addBread(bread: Bread) =
            apply {
                this.bread = bread
            }

        fun addCheese(cheese: Cheese) =
            apply {
                this.cheese = cheese
            }

        fun addMayo(mayo: Mayonnaise) = apply { this.mayo = mayo }

        fun build() = Burger(meat, bread, cheese, mayo)
    }
}

data class Meat(
    val name: String,
)

data class Bread(
    val name: String,
)

data class Cheese(
    val name: String,
)

data class Mayonnaise(
    val name: String,
)

fun main() {
    val burger =
        Burger
            .Builder()
            .addMeat(Meat("Beed"))
            .addBread(Bread("Wheat bread"))
            .build()
    burger.serve()
}
