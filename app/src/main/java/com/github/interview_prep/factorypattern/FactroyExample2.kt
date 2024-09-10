package com.github.interview_prep.factorypattern

interface FileParser

class XmlFileParser : FileParser

class JsonFileParser : FileParser

fun interface FileParserFactory {
    fun createFromFileName(fileName: String): FileParser
}

class CompanionObjectFileParserFactory {
//    companion object : FileParserFactory {
//        override fun createFromFileName(fileName: String) =
//            when (fileName.substringAfterLast('.')) {
//                "xml" -> XmlFileParser()
//                "json" -> JsonFileParser()
//                else -> throw Exception("I don't know how to deal with $fileName.")
//            }
//    }

    companion object {
        val parser =
            FileParserFactory { fileName ->
                when (fileName.substringAfterLast('.')) {
                    "xml" -> XmlFileParser()
                    "json" -> JsonFileParser()
                    else -> throw Exception("I don't know how to deal with $fileName.")
                }
            }

        fun parser(fileName: String) {
            FileParserFactory { fileName ->
                when (fileName.substringAfterLast('.')) {
                    "xml" -> XmlFileParser()
                    "json" -> JsonFileParser()
                    else -> throw Exception("I don't know how to deal with $fileName.")
                }
            }
        }
    }

    // needed if you want to be able to call the createFromFileName from an instance
//    override fun createFromFileName(fileName: String): FileParser {
//        println("createFromFileName called")
//        return CompanionObjectFileParserFactory.createFromFileName(fileName)
//    }
}

fun main() {
    val parser = CompanionObjectFileParserFactory.parser("wewe")
//    println(parser.createFromFileName("data.xml"))
//    println(parser.createFromFileName("data.json"))
//    val parser2 = CompanionObjectFileParserFactory()
//    parser2.createFromFileName("")
}
