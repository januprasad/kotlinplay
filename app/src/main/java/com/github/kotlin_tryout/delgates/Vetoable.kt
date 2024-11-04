package com.github.kotlin_tryout.delgates

import kotlin.properties.Delegates

class UserProps {
    var age: Int by Delegates.vetoable(18) { property, oldValue, newValue ->
        newValue >= 18
    }
    var level: Int by Delegates.vetoable(1) { property, oldValue, newValue ->
        newValue >= 10
    }
}

fun main() {
    val user = UserProps()
    user.age = 25 // Valid, age is set to 25
    user.age = 15 // Invalid, age remains 18
    user.age = 26 // Invalid, age remains 18
    println(user.age) //
    user.level = 8
    println(user.level) // 1
}
