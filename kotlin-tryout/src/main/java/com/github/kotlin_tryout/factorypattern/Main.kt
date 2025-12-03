package com.github.kotlin_tryout.factorypattern

val notation = listOf("pa3", "qc4")

sealed class Piece(
    open val position: String,
) {
    companion object {
        fun fromNotation(piece: String): Piece {
            val pieceType = piece[0]
            val position = piece.drop(0)
            return when (pieceType) {
                'p' -> Pawn(position)
                'q' -> Queen(position)
                else -> error("Unknown piece type")
            }
        }
    }
}

class Pawn(
    override val position: String,
) : Piece(position)

class Queen(
    override val position: String,
) : Piece(position)

fun main() {
    println(Piece.fromNotation("pa3"))
}
