package com.github.kotlin_tryout.decoratorpattern

fun main() {
}

interface Printer {
    fun printMessage(msg: String)
}

class ConsolePrinter(
    printer: Printer,
) : DecoratorPrinter(printer) {
    override fun printMessage(msg: String) = printer.printMessage(msg)
}

class InkjetPrinter(
    printer: Printer,
) : DecoratorPrinter(printer) {
    override fun printMessage(msg: String) = printer.printMessage(msg)
}

abstract class DecoratorPrinter(
    val printer: Printer,
) : Printer
