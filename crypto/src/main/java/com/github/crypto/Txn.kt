package com.github.crypto

import java.security.MessageDigest

// Function to create a transaction hash
fun createTransactionHash(transactionData: ByteArray): ByteArray {
    val digest = MessageDigest.getInstance("SHA-256")
    val hash1 = digest.digest(transactionData)
    val hash2 = digest.digest(hash1) // Double hashing for some blockchain platforms
    return hash2
}

// Example usage
fun main() {
    // Example transaction data (serialized byte array)
    val transactionData = "Transaction data to be hashed".toByteArray(Charsets.UTF_8)

    // Create transaction hash
    val transactionHash = createTransactionHash(transactionData)

    // Print transaction hash (hexadecimal format)
    println("Transaction Hash: ${transactionHash.joinToString("") { "%02x".format(it) }}")
}
