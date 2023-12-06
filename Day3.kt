import java.io.BufferedReader
import java.io.FileReader

fun main() {
    print(day3part1())
}

val matrix = mutableListOf<List<Char>>()
fun day3part1(): Int {

    fillMatrix()
    var isAdjacent = false
    var number = 0
    var sum = 0
    for (row in 0 until matrix.size) {
        for (column in 0 until matrix[row].size) {
            val currentTile = matrix[row][column]
            if (currentTile == '.' || !currentTile.isDigit()) {
                continue
            } else if (column + 1 < matrix.size && matrix[row][column + 1].isDigit()) {
                if (!isAdjacent) isAdjacent = checkAround(row, column)
                number = if (number == 0) currentTile.digitToInt() else number * 10 + currentTile.digitToInt()

            } else if ((column + 1 < matrix.size && !matrix[row][column + 1].isDigit()) || column + 1 >= matrix.size) {
                if (!isAdjacent) isAdjacent = checkAround(row, column)
                if (isAdjacent) {
                    sum += if (number != 0) number * 10 + currentTile.digitToInt() else currentTile.digitToInt()
                }
                isAdjacent = false
                number = 0
            }

        }
    }
    return sum
}

//fun day3part2(): Int {}



fun checkAround(row: Int, col: Int): Boolean {
    for (i in -1..1) {
        for (j in -1..1) {
            if (0 <= row + i && row + i < matrix.size && 0 <= col + j && col + j < matrix.size) {
                if (!matrix[row + i][col + j].isDigit() && matrix[row + i][col + j] != '.') return true
            }
        }
    }
    return false
}






fun fillMatrix() {
    val fileName = "inputs/day3input"
    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            val list = mutableListOf<Char>()
            for (char in line) {
                list.add(char)
            }
            matrix.add(list)
            line = reader.readLine()
        }
    }
}

