package com.github.interview_prep.factorypattern

val notation = listOf("pa3", "qc4")

sealed class Piece(
    open val position: String,
)

class Pawn(
    override val position: String,
) : Piece(position)

class Queen(
    override val position: String,
) : Piece(position)

fun generatePieces() =
    notation.map { piece ->
        val pieceType = piece[0]
        val position = piece.substring(1)
        when (pieceType) {
        }
    }
