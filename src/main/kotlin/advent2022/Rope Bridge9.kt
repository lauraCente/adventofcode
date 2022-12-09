package advent2022

import java.lang.Math.abs
import kotlin.io.path.Path
import kotlin.io.path.readText


data class Point(val x: Int, val y: Int) {
//    var visits = 1
//    val visited: Boolean get() = this.visits > 0
//
//    fun copy() {
//        val p = Point(this.x, this.y)
//    }
}

fun main() {

    val moves = Path("src/main/resources/2022day9_1.txt").readText().split("\n").map { it.split(" ") }
    println(moves)
    var head = Point(0, 0)
    val tail = mutableListOf<Point>(head)

    for (move in moves) {
        repeat(move[1].toInt()) {
            val headMoved: Point = moveHead(head, move)
            val tailMoved: Point? = moveTail(head, headMoved, tail.last())
            head = headMoved
            if (tailMoved != null) tail.add(tailMoved)
        }

    }
    val tailSet = tail.toSet()
    println(tailSet)
    println(tailSet.size)


}

/*
si distancia > 2
(>=1,?) -> (1,0)
(<=1,?) -> (-1,0)
(?,>=1) -> (0,1)
(?,<=1) -> (0,-1)
5,1 - 3,0

 */

fun moveTail(lastHead: Point, currentHead: Point, tail: Point): Point? {
    val distance1 = distanceMax1(currentHead, tail)
    val point = if (!distance1) lastHead else null
    return point
}

fun distanceMax1(currentHead: Point, tail: Point): Boolean {
    val costat = abs(currentHead.x - tail.x) + abs(currentHead.y - tail.y) == 1
    val diagonal = abs(currentHead.x - tail.x) <= 1 && abs(currentHead.y - tail.y) <= 1
    return costat || diagonal
}

fun moveHead(head: Point, move: List<String>): Point {
    val currentMove: Point
    when (move[0]) {
        "R" -> currentMove = Point(head.x + 1, head.y)
        "D" -> currentMove = Point(head.x, head.y - 1)
        "L" -> currentMove = Point(head.x - 1, head.y)
        else -> currentMove = Point(head.x, head.y + 1)//"U"
    }
    return currentMove
}




