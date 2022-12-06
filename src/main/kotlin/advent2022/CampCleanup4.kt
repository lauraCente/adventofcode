package advent2022

import advent2022.utils.contains
import advent2022.utils.fullyContains
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val list =
        Path("src/main/resources/2022day4_1.txt").readText().split("\n").map { it -> it.split(",").map { it.toRange() } }

    val result1 = list.count { it[0].fullyContains(it[1]) || it[1].fullyContains(it[0]) }
    println(result1)

    val result2 = list.count { it[0].contains(it[1]) }
    println(result2)
}


/**
 * String "4-6" -> [4,-,6]
 */
private fun String.toRange(): IntRange {
    val elements = this.split("-")
    return IntRange(elements[0].toInt(), elements[elements.lastIndex].toInt())
}
