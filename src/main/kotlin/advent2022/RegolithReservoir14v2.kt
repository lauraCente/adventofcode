package advent2022v2

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.max
import kotlin.math.min

data class SandFalling(var x: Int, var y: Int, var lastMoveExecuted: Boolean)

//                                      20
val matrix = List<MutableList<Int>>(179) { MutableList<Int>(680) { 0 } }
fun main() {

    val lines = Path("src/main/resources/2022day14_1.txt").readText().split("\n")
        .map { it.split("->").map { it.split(",").map { it.trim().toInt() } } }

    for (line in lines) {
        var pointA = line[0]
        var pointB: List<Int>
        for (i in 1..line.lastIndex) {
            pointB = line[i]
            drawLine(pointA, pointB)
            pointA = pointB
        }
    }
    putFloor()
    paintMatrix()
    var sand = 1
    var sandFalling = throwSand(SandFalling(0, 500, true))
    while (sandFalling.x > 0) {
        sandFalling = throwSand(SandFalling(0, 500, true))
        sand++
    }
    paintMatrix()
    println(sand)
}

fun paintMatrix() {
    matrix.forEachIndexed { i, el ->
        el.forEachIndexed { j, ele ->
            if (j > 450 ) {
                var color = "\u001b[0m"
                if (j == 500) {
                    color = "\u001b[31m"
                }
                print(color + ele)
            }
        }
        println()
    }
    println()
    println()
    println()
    println()
}

fun putFloor() {
    for (j in matrix[matrix.lastIndex].indices) {
        matrix[matrix.lastIndex][j] = 1
    }
}

fun throwSand(sandFalling: SandFalling): SandFalling {
    sandFalling.lastMoveExecuted = false
    if (sandFalling.x == matrix.lastIndex) return sandFalling
    var sandFallingT = getDown(sandFalling)
    if (!sandFallingT.lastMoveExecuted) {
        sandFallingT = getDownLeft(sandFalling)
        if (!sandFallingT.lastMoveExecuted) {
            sandFallingT = getDownRight(sandFalling)
        }
    }
    if (!sandFallingT.lastMoveExecuted) {
        val p = place(sandFallingT)
        return p
    } else return throwSand(sandFallingT)
}


fun place(sandFalling: SandFalling): SandFalling {
    matrix[sandFalling.x][sandFalling.y] = 1
    return sandFalling
}

fun getDownRight(sandFalling: SandFalling): SandFalling {

    var sand: SandFalling
    if (matrix[sandFalling.x + 1][sandFalling.y + 1] == 0) {
        sand = SandFalling(sandFalling.x + 1, sandFalling.y + 1, true)
    } else sand = SandFalling(sandFalling.x, sandFalling.y, false)
    return sand
}

fun getDownLeft(sandFalling: SandFalling): SandFalling {
    var sand: SandFalling
    if (matrix[sandFalling.x + 1][sandFalling.y - 1] == 0) {
        sand = SandFalling(sandFalling.x + 1, sandFalling.y - 1, true)
    } else sand = SandFalling(sandFalling.x, sandFalling.y, false)
    return sand
}

fun getDown(sandFalling: SandFalling): SandFalling {
    var sand: SandFalling
    if (matrix[sandFalling.x + 1][sandFalling.y] == 0) {
        sand = SandFalling(sandFalling.x + 1, sandFalling.y, true)
    } else sand = SandFalling(sandFalling.x, sandFalling.y, false)
    return sand
}


fun drawLine(pointA: List<Int>, pointB: List<Int>) {
    if (pointA[0] == pointB[0]) {
        val smallRange = min(pointA[1], pointB[1])
        val bigRange = max(pointA[1], pointB[1])
        for (i in smallRange..bigRange) {
            matrix[i][pointA[0]] = 1
        }
    } else {
        val smallRange = min(pointA[0], pointB[0])
        val bigRange = max(pointA[0], pointB[0])
        for (i in smallRange..bigRange) {
            matrix[pointA[1]][i] = 1
        }
    }
}


//131 132