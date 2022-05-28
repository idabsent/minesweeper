package minesweeper

import kotlin.random.Random

class Field(
    size: Int,
    private val mineR: Char,
    private val freeR: Char
){
    private val rcCount = 9

    private var field = MutableList(rcCount) { MutableList(rcCount) { false } }

    private var generated = 0

    private fun borderMinesCount(row: Int, column: Int) : Int {
        val leftBorder = if (column > 0) { column - 1 } else { column }
        val rightBorder = if (column < field.first().lastIndex) { column + 1 } else { field.first().lastIndex }
        val topBorder = if (row > 0) { row - 1 } else { row }
        val bottomBorder = if (row < field.lastIndex) { row + 1 } else { field.lastIndex }

        var count = 0

        for (lRow in topBorder..bottomBorder) {
            for (lColumn in leftBorder..rightBorder) {
                if (lRow == row && lColumn == column) continue
                if (field[lRow][lColumn]) count++
            }
        }

        return count
    }
    private val representation: String
        get() {
            return this.field.mapIndexed() { row, columns ->
                columns.mapIndexed { column, value ->
                    if (value) { mineR } else {
                        val borderMines = borderMinesCount(row, column)
                        if (borderMines == 0) { freeR } else { borderMines.toString() }
                    }
                }.joinToString("")
            }.joinToString("\n")
        }

    init {
        while (generated < size) {
            val randomRow = Random.nextInt(rcCount)
            val randomColumn = Random.nextInt(rcCount)

            if (!field[randomRow][randomColumn]) {
                field[randomRow][randomColumn] = true
                generated++
            }
        }

    }

    fun getField() : String {
        return representation
    }
}

fun main(args: Array<String>) {
    print("How many mines do you want on the field? > ")

    val size = readln().toInt()

    val field = Field(size, 'X', '.')

    println(field.getField())
}