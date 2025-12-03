package com.github.kotlin_tryout.delgates

interface Printer {
    fun printPaper(message: String)
}

class ConsolePrinter : Printer {
    override fun printPaper(message: String) {
        println("$message using pen")
    }
}

class InkjetPrinter : Printer {
    override fun printPaper(message: String) {
        println("$message using inkject")
    }
}

class PrinterDelegate(
    private val printer: Printer,
) : Printer by printer

fun main() {
    val delegatedPrinter: Printer = PrinterDelegate(ConsolePrinter())
    delegatedPrinter.printPaper("Hello")

    val delegatedPrinter2: Printer = PrinterDelegate(InkjetPrinter())
    delegatedPrinter2.printPaper("Hello")
}
