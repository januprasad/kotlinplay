package com.example.rx_android_jav

import android.annotation.SuppressLint
import android.util.SparseArray

class ShoppingCart {
    @SuppressLint("UseSparseArrays")
    private val productQuantities = SparseArray<Int>()

    fun addToCart(
        productId: Int,
        quantity: Int,
    ) {
        productQuantities.put(productId, quantity)
    }

    fun getQuantity(productId: Int): Int {
        return productQuantities.get(productId, 0) // Return 0 if product not in cart
    }

    fun removeProduct(productId: Int) {
        productQuantities.remove(productId)
    }

    fun getTotalQuantity(): Int {
        var totalQuantity = 0
        for (i in 0 until productQuantities.size()) {
            val quantity = productQuantities.valueAt(i)
            totalQuantity += quantity
        }
        return totalQuantity
    }
}
