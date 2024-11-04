package com.github.kotlin_tryout.inlines

import java.security.MessageDigest
import kotlin.random.Random

fun main() {
    val oldAuthToken = hash(Random(100000).nextInt(1, 2000000).toString())
    getAuthToken(
        oldAuthToken = oldAuthToken,
        newToken = {
            println(it)
        },
        reuse = {
            println(oldAuthToken)
        },
    )
}

fun hash(number: String): String = MessageDigest.getInstance("MD5").digest("number".toByteArray()).toString()

inline fun getAuthToken(
    oldAuthToken: String,
    crossinline newToken: (String) -> Unit,
    noinline reuse: () -> Unit,
) {
    if (oldAuthToken.isExpired()) {
        getResource {
            newToken(hash(Random(100000).nextInt(1, 2000000).toString()))
        }
    } else {
        getResource(readResource = reuse)
    }
}

fun String.isExpired(): Boolean = true

fun getResource(readResource: () -> Unit) {
    readResource()
}

fun getFile(): String = "File"
