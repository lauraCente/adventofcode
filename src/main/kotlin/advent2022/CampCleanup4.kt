package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val list =
        Path("src/main/resources/2022day4_1.txt").readText().split("\n").map { it.split(",").map { it.toRange() } }

    var result1 = list.count { it[0].fullyContains(it[1]) || it[1].fullyContains(it[0]) }
    println(result1)

    var result2 = list.count { it[0].contains(it[1]) }
    println(result2)
}

fun IntRange.fullyContains(r1: IntRange): Boolean {
    return r1.first >= this.first && r1.last <= this.last
}

/**
 * 4 5 6  5 6 7
 */
fun IntRange.contains(r1: IntRange): Boolean {
    return r1.toList().any { it in this }
}

/*
2-4 to range
 */
private fun String.toRange(): IntRange {
    var elements = this.split("-")
    return IntRange(elements[0].toInt(), elements[elements.lastIndex].toInt())

}
