package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.max


fun main() {

    val moves = Path("src/main/resources/2022day17_t.txt").readText()
    var pyro = Pyroclastic()

    var shape = createShape(pyro)
    for (move in moves) {
        if(pyro.numShape==2023){
            break
        }
        when (move) {
            '>' -> {
                if (shape.canImoveRight(pyro.zone)) {
                    shape.moveRight(pyro)
                    if (shape.canImoveDown(pyro.zone)) shape.moveDown()
                    else {
                        shape.putShapeInMatrix(pyro)
                        shape = createShape(pyro)
                    }
                } else {
                    if (shape.canImoveDown(pyro.zone)) shape.moveDown()
                    else {
                        shape.putShapeInMatrix(pyro)
                        shape = createShape(pyro)
                    }
                }
            }
            else -> { //<
                if (shape.canImoveLeft(pyro.zone)) {
                    shape.moveLeft(pyro)
                    if (shape.canImoveDown(pyro.zone)) shape.moveDown()
                    else {
                        shape.putShapeInMatrix(pyro)
                        shape = createShape(pyro)
                    }
                } else {
                    if (shape.canImoveDown(pyro.zone)) shape.moveDown()
                    else {
                        shape.putShapeInMatrix(pyro)
                        shape = createShape(pyro)
                    }
                }
            }
        }
    }
    println(pyro.high)


}

fun createShape(pyro: Pyroclastic): Shape {
    var shape: Shape
    val unitsAbove = 4
    when (pyro.numShape % 5) {
        0 -> shape = Shape1(pyro.high + unitsAbove)
        1 -> shape = Shape2(pyro.high + unitsAbove)
        2 -> shape = Shape3(pyro.high + unitsAbove)
        3 -> shape = Shape4(pyro.high + unitsAbove)
        else -> shape = Shape5(pyro.high + unitsAbove)
    }
    return shape
}

class Pyroclastic() {
    val zone = List<MutableList<Int>>(400000) { MutableList<Int>(7) { 0 } }
    var high = -1
    var numShape = 0

    fun print() {
        for (i in 50 downTo 0 ) {
            for (j in 0..6) {
                print(zone[i][j])
            }
            println()
        }
        println()
        println()
    }


}

class P(var x: Int, var y: Int)
abstract class Shape(var level: Int) {
    abstract val points: MutableList<P>

    fun canImoveLeft(pyroclastic: List<MutableList<Int>>): Boolean {
        var canIMove = true
        for (point in points) {
            if (point.x == 0 || pyroclastic[point.y][point.x - 1] != 0) {
                canIMove = false
                break
            }
        }
        return canIMove
    }

    fun canImoveRight(pyroclastic: List<MutableList<Int>>): Boolean {
        var canIMove = true
        for (point in points) {
            if (point.x >= 6 || pyroclastic[point.y][point.x + 1] != 0) {
                canIMove = false
                break
            }
        }
        return canIMove
    }

    fun canImoveDown(pyroclastic: List<MutableList<Int>>): Boolean {
        var canIMove = true
        for (point in points) {
            if (point.y == 0 || pyroclastic[point.y - 1][point.x] != 0) {
                canIMove = false
                break
            }
        }
        return canIMove
    }

    fun moveRight(pyroclastic: Pyroclastic) {
        points.forEach {  it.x = it.x + 1 }
    }

    fun moveDown() {
        for (p in points) {
            p.y = p.y - 1
        }
    }

    fun moveLeft(pyroclastic: Pyroclastic) {
        points.forEach {
            it.x = it.x - 1
        }
    }

    fun putShapeInMatrix(pyroclastic: Pyroclastic) {
        for (point in points) {
            pyroclastic.zone[point.y][point.x] = 1
        }
        pyroclastic.high = max(pyroclastic.high, points.maxOf { it.y })
        pyroclastic.numShape++
        pyroclastic.print()
    }
}

/**
 * |..@@@@.|
 */
class Shape1(level: Int) : Shape(level) {
    override val points = mutableListOf<P>(P(2, level), P(3, level), P(4, level), P(5, level))
}

/**
 * |...@...|
 * |..@@@..|
 * |...@...|
 */
class Shape2(level: Int) : Shape(level) {
    override val points =
        mutableListOf<P>(P(3, level), P(2, level + 1), P(3, level + 1), P(4, level + 1), P(3, level + 2))
}

/**
 *   ..#
 *  ..#
 *  ###
 */
class Shape3(level: Int) : Shape(level) {
    override val points = mutableListOf<P>(P(2, level), P(3, level), P(4, level), P(4, level + 1), P(4, level + 2))
}

/**
 *  #
 *  #
 *  #
 *  #
 */
class Shape4(level: Int) : Shape(level) {
    override val points = mutableListOf<P>(P(2, level), P(2, level + 1), P(2, level + 2), P(2, level + 3))
}

/**
 *  ##
 *  ##
 */
class Shape5(level: Int) : Shape(level) {
    override val points = mutableListOf<P>(P(2, level), P(3, level), P(2, level + 1), P(3, level + 1))
}