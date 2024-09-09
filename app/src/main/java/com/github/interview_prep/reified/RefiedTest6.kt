package com.github.interview_prep.reified

fun main() {
    val calc = createInstance<Person>("Name", 12)
//    val calc = instance<Calc>()
    println(calc?.name)
}

// class Person {
//    val name = "Abc"
// }

data class Person(
    val name: String,
    val age: Int,
)

// inline fun <reified T : Person> instance(): Person = T::class.java.getDeclaredConstructor().newInstance()

// inline fun <reified T : Any> instanceFruit(): Any = T::class.java.getDeclaredConstructor().newInstance("1", 1)
inline fun <reified T : Calc> instance(): Calc {
    val constructor =
        T::class.java.declaredConstructors
    return Calc("23")
}

inline fun <reified T : Any> createInstance(vararg args: Any): T? =
    try {
        // Get the Class object for the reified type
        val clazz = T::class.java

        // Find the appropriate constructor based on the argument types
        val constructor =
            clazz.declaredConstructors
                .firstOrNull { ctor ->
                    val paramTypes = ctor.parameterTypes
                    paramTypes.size == args.size &&
                        paramTypes.zip(args.map { it::class.javaObjectType }).all { (paramType, argType) ->
                            paramType.isAssignableFrom(argType)
                        }
                }

        // If constructor is found, create a new instance using it
        constructor?.newInstance(*args) as? T
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

class Calc(
    val age: String,
) : ICalc {
    override val name: String = "Calc"
}

interface ICalc {
    val name: String
        get() = "ICalc"
}
