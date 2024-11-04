package com.github.solid

import javax.print.PrintService
import kotlin.random.Random

class SolidDemo {
    //S
    //O
    //L
    //I
    //D
}

//Single Responsibility
class LoginAuth {
    private fun login() {

    }
}

class TokenValidation {
    private fun validateToken() {

    }
}

//Open Closed
//Open for extension and closed for modification
interface NotificationService {
    fun sendNotification()
}

class EmailNotificationService : NotificationService {
    override fun sendNotification() {
        TODO("Not yet implemented")
    }

}

class PushNotificationService : NotificationService {
    override fun sendNotification() {
        TODO("Not yet implemented")
    }

}

class SMSNotificationService : NotificationService {
    override fun sendNotification() {
        TODO("Not yet implemented")
    }
}

// Liskov Substitution

/** in this example it breaks, we need to split contracts
 * interface Waste {
 *     fun dispose()
 * }
 *
 * class OrganicWaste : Waste {
 *     override fun dispose() {
 *         println("using composte")
 *     }
 * }
 *
 * class PlasticWaste : Waste {
 *     override fun dispose() {
 *         println("using recycle")
 *     }
 * }
 */

interface Recycle {
    fun recycle()
}

interface Compose {
    fun compose()
}
interface Waste {
    fun dispose()
}

class OrganicWaste : Waste, Compose {
    override fun dispose() {
        compose()
    }

    override fun compose() {
        println("using compose method")
    }
}

class PlasticWaste : Waste, Recycle {
    override fun dispose() {
        recycle()
    }

    override fun recycle() {
        println("using recycle")
    }
}

//Interface Segregation

interface ClickListener {
    fun onClick()
}

interface LongClickListener {
    fun onLongClick()
}

class Button : ClickListener, LongClickListener {
    override fun onClick() {
        TODO("Not yet implemented")
    }

    override fun onLongClick() {
        TODO("Not yet implemented")
    }
}

//Dependency Inversion
interface Vehicle {
    fun move()
}

class Car : Vehicle {
    override fun move() {
        println("going in car :)")
    }
}

class Bus : Vehicle {
    override fun move() {
        println("going in bus :(")
    }
}

//fun main() {
//
//    val days = 7
//    repeat(days) {
//        val r = Random(it).nextInt() % 2 == 0
//        when(r) {
//            true -> travel(Car())
//            false -> travel(Bus())
//        }
//    }
//}

fun travel(vehicle: Vehicle) {
    vehicle.move()
}