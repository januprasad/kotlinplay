package com.github.kotlin_tryout.varargs

fun main() {
    processRecords("test", "test1", "test2", "test3")
}

fun processRecords(vararg records: String)  {
    for (record in records) {
        println(record)
    }
}
