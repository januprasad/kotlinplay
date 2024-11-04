package com.github.kotlin_tryout.covariance

interface View {
    fun callOnClick()
}

interface EventListener<in T> {
    fun onEvent(event: T)
}

open class Button : View {
    override fun callOnClick() {
        println("Button clicked!")
    }
}

class OutlinedButton : Button() {
    override fun callOnClick() {
        println("OutlinedButton clicked!")
    }
}

class CorneredButton : Button() {
    override fun callOnClick() {
        println("CorneredButton clicked!")
    }
}

class OnClickListener : EventListener<View> {
    override fun onEvent(event: View) {
        event.callOnClick()
    }
}

fun main() {
    val listener: EventListener<View> = OnClickListener()
    invokeButtonAction(listener, Button())
    invokeButtonAction(listener, OutlinedButton()) // if OutlinedButton is subtype of Button then only it accepts click events
    invokeButtonAction(listener, CorneredButton()) // if CorneredButton is subtype of Button then only it accepts click events
}

fun invokeButtonAction(
    listener: EventListener<Button>,
    view: Button,
) {
    listener.onEvent(view)
}
