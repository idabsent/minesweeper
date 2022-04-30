package minesweeper

import kotlin.random.Random

class Field(
    private val size: Int,
    private val mineR: Char,
    private val freeR: Char
){
    private val rcCount = 9

    private var field: MutableList<MutableList<Boolean>>

    private var generated = 0

    private val representation: String
        get() {
            return this.field.map {
                it.map {
                    if (it) { mineR } else { freeR }
                }.joinToString("")
            }.joinToString("\n")
        }

    init {
        field = MutableList(rcCount) {
            MutableList(rcCount) { false }
        }

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

    val field = Field(5, 'X', '.')

    println(field.getField())
}