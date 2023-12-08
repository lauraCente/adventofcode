package advent2023

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    var lines = Path("src/main/resources/2023day2_2.txt").readText().split("\n")

    val MAX_RED_CUBES = 12
    val MAX_GREEN_CUBES = 13
    val MAX_BLUE_CUBES = 14

    var total = 0
    for (i in lines.indices) {
        var game = lines[i].split(":")
        var sets = game[1].split(";")
        var impossible = false
        for (set in sets) {
            var combinations = set.split(",")
            for (combination in combinations) {
                var (number, color) = combination.split(" ").map { it.trim() }.filter { it != "" }
                var num = number.toInt()
                when (color) {
                    "red" -> if (num > MAX_RED_CUBES) impossible = true
                    "green" -> if (num > MAX_GREEN_CUBES) impossible = true
                    "blue" -> if (num > MAX_BLUE_CUBES) impossible = true
                }
                if (impossible) break
            }
            if (impossible) break
        }
        if (!impossible) {
            total = total + (i + 1)
            println(i + 1)
        }
        impossible = false
    }
    println(total)

}