package com.github.interview_prep

fun main() {

}
inline fun guard( condition: Boolean, body: () -> Void ){
    if( !condition )
        body()
}

