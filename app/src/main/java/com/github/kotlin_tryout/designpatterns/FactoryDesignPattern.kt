package com.github.kotlin_tryout.designpatterns

class Aviyal private constructor(
    private val turmeric: Boolean,
    private val spicy: Boolean,
    private val sour: Boolean,
    val style: AviyalType,
) {
    fun cook() {
        val turmericState = if (turmeric) "with turmeric" else "without turmeric"
        val spicyState = if (spicy) "more spicy" else "less spicy"
        val sourState = if (sour) "is sour" else "less sour"

        val result =
            """
            Aviyal cooked in $style 
            $turmericState, 
            $spicyState, and 
            $sourState
            """

        println(result)
    }

    companion object Factory {
        fun create(style: AviyalType): Aviyal =
            when (style) {
                AviyalType.ThekkanStyle -> Aviyal(false, true, false, style)
                AviyalType.VadakkanStyle -> Aviyal(false, false, false, style)
                AviyalType.MadhykeralamStyle -> Aviyal(true, false, true, style)
            }
    }
}

fun main() {
    Chef().cookAviyal(AviyalType.MadhykeralamStyle)
}

class Chef {
    fun cookAviyal(type: AviyalType) {
        val aviyal = Aviyal.create(type)
        aviyal.cook() // Separate cooking logic for clarity
    }
}

sealed class AviyalType {
    data object ThekkanStyle : AviyalType()

    data object VadakkanStyle : AviyalType()

    data object MadhykeralamStyle : AviyalType()
}
