import java.io.BufferedReader
import java.io.FileReader

fun main() {
    println(day4part1())
    println(day4part2())
}

fun day4part1(): Int {

    val fileName = "inputs/day4input"

    var cardPoints = 0
    var sum = 0
    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            val numberMap = mutableMapOf<String, Boolean>()
            //trim line
            line = line.substringAfter(":")
            val luckyNumbers = line.substringBefore("|").trim().split("\\s+".toRegex())
            val myNumbers = line.substringAfter("|").trim().split("\\s+".toRegex())
            for (number in luckyNumbers) {
                numberMap[number] = true
            }
            for (number in myNumbers) {
                if (numberMap[number] != null) {
                    cardPoints = if (cardPoints == 0) 1 else cardPoints * 2

                }
            }
            sum += cardPoints
            cardPoints = 0
            line = reader.readLine()
        }
    }
    return sum
}

fun day4part2(): Int {

    val fileName = "inputs/day4input"

    var numberOfMatches = 0
    var numberOfCards = 0
    //Stores number of cards (Copies and Originals)
    val copiesMap = mutableMapOf<Int, Int>()
    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            //Stores lucky numbers
            val numberMap = mutableMapOf<String, Boolean>()

            val cardNumber = line.substringBefore(":").split("\\s+".toRegex())[1].toInt()

            line = line.substringAfter(":")
            val luckyNumbers = line.substringBefore("|").trim().split("\\s+".toRegex())
            val myNumbers = line.substringAfter("|").trim().split("\\s+".toRegex())

            if(copiesMap[cardNumber] == null) copiesMap[cardNumber] = 1 else copiesMap[cardNumber] = copiesMap[cardNumber]!!.inc()

            //Counts matches
            for (number in luckyNumbers) {
                numberMap[number] = true
            }
            for (number in myNumbers) {
                if (numberMap[number] != null) {
                    numberOfMatches++
                }
            }
            //Counts number of copies
            for (i in cardNumber + 1..cardNumber + numberOfMatches) {
                if (copiesMap[i] == null) copiesMap[i] = 1 * copiesMap[cardNumber]!! else copiesMap[i] =
                    copiesMap[i]!!.plus(1 * copiesMap[cardNumber]!!)
            }

            numberOfMatches = 0
            line = reader.readLine()
        }

        for (element in copiesMap) {
            numberOfCards += element.value
        }
    }
    return numberOfCards
}