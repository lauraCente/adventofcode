package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText

data class Square(var x: Int, var y: Int, var elevation: Int, var letter: Char) {
    var isVisited = false
    var stepToArrive: Int? = null
    fun visited() {
        isVisited = true
    }
}

fun main() {
    val lists = Path("src/main/resources/2022day12_1.txt").readText().split("\n")
    val elements = lists.map { it.split("").filter { it != "" } }
    val matrix = elements.mapIndexed { i, list ->
        list.mapIndexed { j, c ->
            if (c == "E") Square(i, j, 'z'.code - 96, 'E') else Square(i, j, c.first().code - 96, c.first())
        }
    }
    val starters = matrix.flatten().filter { it.letter == 'a' || it.letter == 'S' }
    for (starter in starters) {
        //matrix[20][0].stepToArrive = 0
        val matrixInit = copy(matrix)
        matrixInit[starter.x][starter.y].stepToArrive = 0
        var step = searchE(matrixInit[starter.x][starter.y], matrixInit)
        println(matrixInit[20][40].stepToArrive)
    }

//    for (i in matrix.indices) {
//        for (j in matrix[i].indices) {
//            print("${matrix[i][j].stepToArrive} ")
//        }
//        println()
//    }
//    var last = matrix.flatten().maxByOrNull { it.stepToArrive ?: 0 }
//    println(matrix[20][40].stepToArrive)
}

fun copy(matrix: List<List<Square>>): List<List<Square>> {
    val copy = mutableListOf<List<Square>>()
    for (i in matrix.indices) {
        val line = mutableListOf<Square>()
        for (j in matrix[i].indices) {
            line += matrix[i][j].copy()
        }
        copy.add(line.toList())
    }
    return copy
}

fun searchE(current: Square, matrix: List<List<Square>>) {
    current.elevation = 1
    current.visited()
    var neighbours = getNeighbours(current, matrix)
    val acNeighbours =
        neighbours.filter { it.isVisited == false && (it.elevation <= current.elevation || it.elevation - 1 == current.elevation) }
    acNeighbours.forEach { if (it.stepToArrive == null) it.stepToArrive = current.stepToArrive!! + 1 }
    val accessibleNeighbours = acNeighbours.toMutableList()

    var square: Square
    while (!accessibleNeighbours.isEmpty()) {
        square = accessibleNeighbours.minByOrNull { element -> element.stepToArrive!! }!!

        if (square.letter == 'E') {
            break
        }
        if (!square.isVisited) {
            neighbours = getNeighbours(square, matrix)
            val an =
                neighbours.filter { it.isVisited == false && (it.elevation <= square.elevation || it.elevation <= square.elevation + 1) }
            an.forEach { if (it.stepToArrive == null) it.stepToArrive = square.stepToArrive!! + 1 }
            accessibleNeighbours += an
            square.visited()
            accessibleNeighbours.remove(square)
        } else {
            accessibleNeighbours.remove(square)
        }
    }
}

fun getNeighbours(current: Square, matrix: List<List<Square>>): MutableList<Square> {
    val neighbours = mutableListOf<Square>()
    if (current.x < matrix.lastIndex) neighbours += matrix[current.x + 1][current.y]
    //if (current.x < matrix[0].lastIndex && current.y <= matrix.lastIndex) neighbours += matrix[current.x + 1][current.y + 1]
    if (current.y < matrix[0].lastIndex) neighbours += matrix[current.x][current.y + 1]
    //if (current.x > 0 && current.y < matrix.lastIndex) neighbours += matrix[current.x - 1][current.y + 1]
    if (current.x > 0) neighbours += matrix[current.x - 1][current.y]
    //if (current.x > 0 && current.y > 0) neighbours += matrix[current.x - 1][current.y - 1]
    if (current.y > 0) neighbours += matrix[current.x][current.y - 1]
    //if (current.x < matrix[0].lastIndex && current.y > 0) neighbours += matrix[current.x + 1][current.y - 1]
    return neighbours
}

