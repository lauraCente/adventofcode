package advent2022.utils


//fun <T> List<List<T>>.forEachIndexed(action: () -> Unit) {
//    for (i in this.indices) {
//        for (j in this[i].indices) {
//            action(i, j, this[i][j])
//        }
//    }
//}

/*

 */
fun List<String>.subListByElement(elementSplit: String): MutableList<MutableList<String>> {
    val listOfLists = mutableListOf<MutableList<String>>()
    val subList = mutableListOf<String>()
    for (element in this) {
        if (element == elementSplit) {
            listOfLists.add(subList.toMutableList())
            subList.clear()
        } else {
            subList += element
        }
    }
    listOfLists.add(subList.toMutableList())
    return listOfLists
}

infix fun IntRange.intersects(other: IntRange): Boolean =
    first <= other.last && last >= other.first

fun IntRange.size(): Int =
    last - first + 1

/**
 * Rang overlaps at some point
 */
fun IntRange.contains(r1: IntRange): Boolean {
    return r1.toList().any { it in this }
}

/**
 * range is in range
 */
fun IntRange.fullyContains(r1: IntRange): Boolean {
    return r1.first >= this.first && r1.last <= this.last
}