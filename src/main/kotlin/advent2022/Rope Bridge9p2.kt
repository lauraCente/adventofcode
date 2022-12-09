package advent2022v2

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
    val head = Point(0, 0)
    val tail = mutableListOf<Point>(head)

    val heads = MutableList<Point>(10) { head }

    for (move in moves) {
        repeat(move[1].toInt()) {
            heads[0] = moveHead(heads[0], move)
            for (i in 1..heads.lastIndex) {
                val tailMoved: Point? = moveTail(heads[i - 1], heads[i])
                if (tailMoved != null) {
                    heads[i] = tailMoved
                    if(i==heads.lastIndex)
                        tail.add(tailMoved)
                }
            }
        }
        println(heads)

    }
    val tailSet = tail.toSet()
    println(tailSet.size)
}

/*
si distancia > 2
(>=1,?) -> (1,0)
(<=1,?) -> (-1,0)
(?,>=1) -> (0,1)
(?,<=1) -> (0,-1)
5,1  3,0  -> 4 1
5,1  3,1 -> 4,1

-5,1 -3,0
 */

fun moveTail(currentHead: Point, tail: Point): Point? {
    val distance1 = distanceMax1(currentHead, tail)
    val point: Point?
    val x: Int
    val y: Int
    if (!distance1) {
        if (currentHead.x > tail.x) x = tail.x + 1
        else if (currentHead.x < tail.x) x = tail.x - 1
        else x = tail.x

        if (currentHead.y > tail.y) y = tail.y + 1
        else if (currentHead.y < tail.y) y = tail.y - 1
        else y = tail.y

        point = Point(x, y)
    } else point = null
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




