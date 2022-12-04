package advent2022.v2

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {

    val lists = Path("src/main/resources/2022day3_t.txt").readText().split("\n")
        .map { it.toList() }

    val res2 =lists.chunked(3).map { (v1, v2, v3) ->
        v1.intersect(v2).intersect(v3).single()
    }.sumOf(::getPriority)

    val res =lists.chunked(3).sumOf { (v1, v2, v3) ->
        val common =v1.intersect(v2).intersect(v3).single()
        getPriority(common)
    }
    println(res)
//    val result = rucksackPart1(lists)
//    println(result)
//    val result2 = rucksackPart2(lists)
//    println(result2)
}

fun rucksackPart2(lists: List<List<Char>>): Int {
    val badge = lists.windowed(3, 3)
    var priority = 0
    for (i in badge.indices) {
        val common = badge[i][0].intersect(badge[i][1]).intersect(badge[i][2])
        priority += getPriority(common.single())
    }
    return priority
}


fun rucksackPart1(lists: List<List<Char>>): Int {
    var priority = 0
    for (i in lists.indices) {
        val components =
            Pair(lists[i].subList(0, lists[i].size / 2), lists[i].subList(lists[i].size / 2, lists[i].size))
        val repeatedItem: Char = getItemInBorthCompartments(components.first, components.second)
        priority += getPriority(repeatedItem)
    }
    return priority
}

fun getPriority(repeatedItem: Char): Int =
    if (repeatedItem.isLowerCase()) repeatedItem.code - 96 else repeatedItem.code - 38

fun getItemInBorthCompartments(first: List<Char>, second: List<Char>): Char {
    return first.intersect(second).first()
}
