package advent2022

import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val lists = Path("src/main/resources/day1_t.txt").readText().split("\n\n").map { it.split("\n") }


    println(lists)
//
//    val scanner = Scanner(Path("src/main/resources/day1_t.txt"))
//    var matrix = readIntsToMatrix("src/main/resources/2021day4_test.txt", 5, 5)


}