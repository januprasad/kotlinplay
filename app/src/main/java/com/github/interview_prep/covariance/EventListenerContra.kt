package com.github.interview_prep.covariance

interface View {
    fun callOnClick()
}

interface EventListener<in T> {
    fun onEvent(event: T)
}

class Button : View {
    override fun callOnClick() {
        println("Button clicked!")
    }
}

class OutlinedButton : View {
    override fun callOnClick() {
        println("OutlinedButton clicked!")
    }
}

class OnClickListener : EventListener<View> {
    override fun onEvent(event: View) {
        event.callOnClick()
    }
}

fun main() {
    val listener: EventListener<View> = OnClickListener()
    val view: View = Button()
    listener.onEvent(view) // No error

    val view1: View = OutlinedButton()
    listener.onEvent(view1) // No error
}
