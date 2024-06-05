package com.github.solid

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
interface PrintMachine {
    fun print()
}

class LaserPrinter : PrintMachine {
    override fun print() {
        println("using LaserPrinter")
    }
}

class InkJet : PrintMachine {
    override fun print() {
        println("using InkJet")
    }
}

class PrintService {
    fun printPaper(machine: PrintMachine) {
        machine.print()
    }
}

//fun main() {
//    PrintService().printPaper(InkJet())
//    PrintService().printPaper(LaserPrinter())
//}

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

fun main() {

    val days = 7
    repeat(days) {
        val r = Random(it).nextInt() % 2 == 0
        when(r) {
            true -> travel(Car())
            false -> travel(Bus())
        }
    }
}

fun travel(vehicle: Vehicle) {
    vehicle.move()
}