package com.github.interview_prep.sealedclasssold

sealed class Mobile {
    data class Android(
        val withHeadset: Boolean,
    ) : Mobile()

    data class Iphone(
        val withHeadset: Boolean,
    ) : Mobile()
}

fun main() {
//    val mob = Mobile.Iphone(withHeadset = false)
//    checkMobile(mob)
    val userId = null
    val userId1 = null

    println(userId.toString())
    println("" + userId)
}

// fun checkMobile(mob: Mobile) {
//    when (mob) {
//        is Mobile.Iphone if mob.withHeadset -> {
//            println("iphone with headset")
//        }
//        is Mobile.Iphone -> {
//            println("iphone")
//        }
//        is Mobile.Android -> println("iphone with android")
//    }
// }
