package com.github.stateflow_sharedflow

fun main() {
    val s = Seller(
        address = Address("678503", "Karhtika Nivas")
    )
    val s1 = s.copy(
        address = s.address.copy(
            zipCode = "678504"
        )
    )
    println(s.address)
    println(s1.address)
    println(s1.updateAddress("343433"))
}

data class Seller(
    val address: Address
)

fun Seller.updateAddress(zipCode: String) =
    this.copy(address = this.address.copy(zipCode = zipCode))

data class Address(val zipCode: String, val addressLine: String)