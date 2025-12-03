package com.github.kotlin_tryout.delgates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DataTypeDelegation {
    var myString: String? = null
        set(value) {
            if (value != null && value.length > 5) {
                field = value
            } else {
                // Handle invalid value, e.g., throw an exception, log an error, or assign a default value
                println("Invalid value: must not be null and have length greater than 5")
            }
        }

    var myString1 by StringDelegate()
}

fun main() {
    val obj = DataTypeDelegation()
    obj.myString = "Hell03"
    obj.myString1 = "Hell"
//    println(obj.myString)
}

class StringDelegate(
    private var value: String? = null,
) : ReadWriteProperty<Any?, String?> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): String? = value

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: String?,
    ) {
        if (value != null && value.length > 5) {
            this.value = value
        } else {
            // Handle invalid value, e.g., throw an exception, log an error, or assign a default value
            println("Invalid value on ${property.name} : must not be null and have length greater than 5")
        }
    }
}
