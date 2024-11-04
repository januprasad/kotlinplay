package com.github.solid

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main() {
    val user = Student()
//    println(user.name)
    user.apply {
        name = "JK"
        age = 19
    }
}

class User {
    var name: String by Delegates.observable("") { property, oldValue, newValue ->
//        println("Property '${property.name}' changed from '$oldValue' to '$newValue'")
    }
}

class Student {
    var isValidated: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        println("Property '${property.name}' changed from '$oldValue' to '$newValue'")
    }
    private val stringValidation: (property: KProperty<*>, oldValue: String, newValue: String) -> Unit =
        { property, oldValue, newValue ->
            if (newValue.isNotBlank()) {
                isValidated = true
            } else {
                isValidated = false
            }
        }
    private val intValidation: (property: KProperty<*>, oldValue: Int, newValue: Int) -> Unit =
        { property, oldValue, newValue ->
            if (newValue > 0) {
                isValidated = true
            } else {
                isValidated = false
            }
        }
    var name: String by Delegates.observable("", stringValidation)
    var age: Int by Delegates.observable(0, intValidation)
}

data class User2(
    val name: String,
)
