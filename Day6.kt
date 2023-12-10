import java.io.BufferedReader
import java.io.FileReader

fun main() {
    println(day6part1())
}


fun day6part1(): Int {
    val fileName = "inputs/day6input"
    val options = mutableListOf<Int>()
    val timesToDistances = mutableListOf<Pair<Int, Int>>()
    BufferedReader(FileReader(fileName)).use { reader ->
        var line: String? = reader.readLine()
        val times = line!!.substringAfter(":").trim().split("\\s+".toRegex())
        line = reader.readLine()
        val distances = line!!.substringAfter(":").trim().split("\\s+".toRegex())
        for (i in distances.indices) {
            //Pair(Time,Distance)
            timesToDistances.add(Pair(times[i].toInt(), distances[i].toInt()))
        }
    }
    for (pair in timesToDistances){
        options.add(calculateOptions(pair.first,pair.second,0,0))
    }

    return options.reduce { acc, i -> acc * i  }
}

fun day6part2(){
    
}

fun calculateOptions(time: Int, distance: Int, options: Int, speed: Int): Int {
    var newOptions = options
    if(time > 0){
    if (time * speed >= distance) newOptions++
        return calculateOptions(time - 1, distance, newOptions, speed + 1)
    }
    else{
        return newOptions
    }

}
