import java.io.BufferedReader
import java.io.FileReader

fun main() {
    println(day5part1())
    println(day5part2())
}

fun day5part1(): Long {
    val finalLocations = mutableListOf<Long>()
    val fileName = "inputs/day5input"

    val lines = mutableListOf<String>()

    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        // Read file into a list
        while (line != null) {
            lines.add(line)
            line = reader.readLine()
        }
    }
    //Extract Original Seeds
    val originalSeeds = lines[0].substringAfter(":").trim().split(" ")
    lines.removeFirst()

    //Iterate through each seed to find its final destination
    for (seed in originalSeeds) {
        val originalSeed = seed.toLong()
        var seedMatch = originalSeed

        var currentLineIndex = 0
        while (currentLineIndex < lines.size) {
            val currentLine = lines[currentLineIndex]
            //Skip non-
            if (currentLine != "" && currentLine[0].isDigit()) {
                //[0] = Destination Range Start
                //[1] = Source Range Start
                //[2] = Range
                val mapEntries = currentLine.split(" ").map { it.toLong() }
                //If a match is found skip all other entries of the map
                if (seedMatch >= mapEntries[1] && seedMatch <= mapEntries[1] + mapEntries[2]) {
                    //Number conversion
                    seedMatch = seedMatch - mapEntries[1] + mapEntries[0]
                    //When match found, skip to next map
                    while (currentLineIndex < lines.size - 1 && (lines[currentLineIndex + 1] == "" || lines[currentLineIndex + 1][0].isDigit())
                    ) {
                        currentLineIndex++
                    }
                }
            }
            currentLineIndex++
        }
        finalLocations.add(seedMatch)
    }

    return finalLocations.sorted()[0]
}


fun day5part2(): Long {
    val finalLocations = mutableListOf<Long>()
    val fileName = "inputs/day5input"

    val lines = mutableListOf<String>()

    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        // Read file into a list
        while (line != null) {
            lines.add(line)
            line = reader.readLine()
        }
    }
    //Extract Original Seeds
    val originalSeedRange = lines[0].substringAfter(":").trim().split(" ").chunked(2)
    lines.removeFirst()

    val originalSeeds = mutableListOf<Long>()
    for(range in originalSeedRange){
        for(i in range[0].toLong()..range[1].toLong()){
            originalSeeds.add(i)
        }
    }

    //Iterate through each seed to find its final destination
    for (seed in originalSeeds) {
        val originalSeed = seed.toLong()
        var seedMatch = originalSeed

        var currentLineIndex = 0
        while (currentLineIndex < lines.size) {
            val currentLine = lines[currentLineIndex]
            //Skip non-
            if (currentLine != "" && currentLine[0].isDigit()) {
                //mapEntries:
                //[0] = Destination Range Start
                //[1] = Source Range Start
                //[2] = Range
                val mapEntries = currentLine.split(" ").map { it.toLong() }
                //If a match is found skip all other entries of the map
                if (seedMatch >= mapEntries[1] && seedMatch <= mapEntries[1] + mapEntries[2]) {
                    //Number conversion
                    seedMatch = seedMatch - mapEntries[1] + mapEntries[0]
                    //When match found, skip to next map
                    while (currentLineIndex < lines.size - 1 && (lines[currentLineIndex + 1] == "" || lines[currentLineIndex + 1][0].isDigit())
                    ) {
                        currentLineIndex++
                    }
                }
            }
            currentLineIndex++
        }
        finalLocations.add(seedMatch)
    }

    return finalLocations.sorted()[0]
}

