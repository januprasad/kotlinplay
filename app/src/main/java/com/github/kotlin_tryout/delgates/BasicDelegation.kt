package com.github.kotlin_tryout.delgates

/**
 * In this example, the VendorMachine,SnackMachine  class delegates the getSnacks()
 * method to the AgentVendorMachine instance, reusing its implementation without code duplication.
 */
interface Base {
    fun print()
}

interface Machine {
    fun getSnacks(): String
}

class VendorMachine : Machine {
    val snacks = listOf("Kitkat", "Cadburry", "Apple Juice", "Moosambi")

    override fun getSnacks(): String = snacks.random()
}

class SnackMachine : Machine {
    val snacks = listOf("Kitkat", "Cadburry")

    override fun getSnacks(): String = snacks.random()
}

class AgentVendorMachine(
    vendorMachine: Machine,
) : Machine by vendorMachine

class BaseImpl(
    val x: Int,
) : Base {
    override fun print() {
        println("BaseImpl $x")
    }
}

class Derived(
    b: Base,
) : Base by b

class Derived1(
    b: Base,
)

fun main() {
    val machine1 = VendorMachine()
    val machine2 = SnackMachine()
    val vendorMachine1 = AgentVendorMachine(machine1)
    val vendorMachine2 = AgentVendorMachine(machine2)
    println(vendorMachine1.getSnacks())
    println(vendorMachine2.getSnacks())
}
