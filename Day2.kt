import java.io.BufferedReader
import java.io.FileReader

fun main() {
    println(day2part1())
    println(day2part2())
}

fun day2part1(): Int {
    val fileName = "inputs/day2input"
    val colorMap = mapOf("red" to 12, "green" to 13, "blue" to 14)
    var sum = 0
    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            var isValidGame = true
            val regex = Regex("Game (\\d+):").find(line)
            val gameNumber = regex!!.groupValues[1].toInt()
            line = line.substringAfter(":")

            val gameValues = line.split(";", ",")
            for (element in gameValues) {
                val numberColorPair = element.split(" ")
                if (numberColorPair[1].toInt() > colorMap[numberColorPair[2]]!!) {
                    isValidGame = false
                    break
                }
            }
            if (isValidGame) sum += gameNumber
            line = reader.readLine()
        }
    }
    return sum
}

fun day2part2(): Int {
    val fileName = "inputs/day2input"
    var sum = 0
    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()

        while (line != null) {
            val colorMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            line = line.substringAfter(":")
            val gameValues = line.split(";", ",")

            for (element in gameValues) {
                val numberColorPair = element.split(" ")
                if (numberColorPair[1].toInt() > colorMap[numberColorPair[2]]!!) {
                    colorMap[numberColorPair[2]] = numberColorPair[1].toInt()
                }
            }
            sum += colorMap["red"]!! * colorMap["blue"]!! * colorMap["green"]!!
            line = reader.readLine()
        }
    }
    return sum
}