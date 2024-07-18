package com.github.crypto

import java.security.MessageDigest

fun sha256(input: String): String {
    val bytes = input.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

fun main() {
    val input = "Hello, World!"
    val hash = sha256(input)
    println("Input: $input")
    println("SHA-256 Hash: $hash")
}
