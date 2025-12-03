package com.github.kotlin_tryout.inlines

fun guide() {
    print("guide start")
    teach({
        print("teach abc")
    }, {
        print("teach xyz")
    })
    print("guide end")
}

inline fun teach(
    abc: () -> Unit,
    noinline xyz: () -> Unit,
) {
    abc()
    xyz()
}

fun main() {
    guide()
}
