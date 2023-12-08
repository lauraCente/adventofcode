package advent2023

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    var lines = Path("src/main/resources/2023day2_2.txt").readText().split("\n")

    var redCubes = 0
    var greenCubes = 0
    var blueCubes = 0

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
                    "red" -> if (num > redCubes) redCubes = num
                    "green" -> if (num > greenCubes) greenCubes = num
                    "blue" -> if (num > blueCubes) blueCubes = num
                }

            }

        }
        var power = redCubes * greenCubes * blueCubes
        println("power game $i : $power")
        redCubes =0
        greenCubes=0
        blueCubes=0
        total=total+power
    }
    println(total)

}