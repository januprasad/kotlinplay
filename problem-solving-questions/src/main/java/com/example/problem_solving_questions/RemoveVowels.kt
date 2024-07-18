package com.example.problem_solving_questions

fun main() {
    val base =
        "Rosetta Code is a programming chrestomathy site." +
            "    The idea is to present solutions to the same" +
            "    task in as many different languages as possible," +
            "    to demonstrate how languages are similar and" +
            "    different, and to aid a person with a grounding" +
            "    in one approach to a problem in learning another."
    val sb = StringBuilder()
    val charArray = base.toCharArray()
    charArray.forEach { s ->
        if (s != 'a' && s != 'e' && s != 'i' && s != 'o' && s != 'u') {
            sb.append(s)
        }
    }
    println(sb.toString())

    println(base.filterNot { it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u' })
}
