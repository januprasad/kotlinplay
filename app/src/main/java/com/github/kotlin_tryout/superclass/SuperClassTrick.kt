package com.github.kotlin_tryout.superclass

interface DustBinManager {
    fun clear() {
        println("Clearing dustbin")
    }

    fun addCleaningProduct(product: String)
}

interface ToiletBinManager {
    open fun clear() {
        println("Clearing toilet bin")
    }

    fun addCleaningProduct(product: String)
}

class Manager :
    DustBinManager,
    ToiletBinManager {
    override fun clear() {
        super<DustBinManager>.clear()
        super<ToiletBinManager>.clear()
        addCleaningProduct("Vim")
    }

    override fun addCleaningProduct(product: String) {
        super<ToiletBinManager>.clear()
        super<ToiletBinManager>.clear()
        super<ToiletBinManager>.clear()
        super<ToiletBinManager>.clear()
        super<ToiletBinManager>.clear()
    }
}

fun main() {
    val manager = Manager()
    manager.clear()
}
