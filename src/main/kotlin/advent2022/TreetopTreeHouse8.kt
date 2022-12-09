package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText


class Tree(val height: Int, var isVible: Boolean = false)

fun main() {
    val text = Path("src/main/resources/2022day8_1.txt").readText()
    val matrix = text.split("\n").map {it.split("").filter { it!="" }.map { Tree(it.toInt()) } }


    matrix.printMatrixHeight()
    val width = matrix[0].size
    val height = matrix.size

    //var visibleEdges = width*2 + (height-2)*2
    //println(visibleEdges)
    var totalVisible=0
    for (fila in matrix){
        totalVisible += visibles(fila)
        totalVisible += visibles(fila.reversed())
    }

    println()
    val transpose = List<MutableList<Tree>>(height){MutableList<Tree>(width){Tree(0)} }
    //transpose.printMatrixHeight()
    for (i in matrix.indices){
        for (j in matrix[i].indices){
            transpose[j][i] = matrix[i][j]
        }
    }

    for (fila in transpose){
        totalVisible += visibles(fila)
        totalVisible += visibles(fila.reversed())
    }

    println(totalVisible)
    //matrix.printMatrixVisible()

}

fun visibles(fila: List<Tree>): Int {
    var maxHeight= fila[0].height
    var countVisibles:Int
    if (!fila[0].isVible){
        countVisibles = 1
        fila[0].isVible = true
    } else countVisibles =0

    for (i in 1..fila.lastIndex){
        if (fila[i].height > maxHeight){
            maxHeight = fila[i].height
            if (!fila[i].isVible){
                countVisibles++
                fila[i].isVible = true
            }
        }
    }
    return countVisibles
}




fun List<List<Tree>>.printMatrixHeight() {
    for(i in this.indices ){
        for (j in this[i].indices){
            print("${this[i][j].height} ")
        }
        println()
    }
}

fun List<List<Tree>>.printMatrixVisible() {
    for(i in this.indices ){
        for (j in this[i].indices){
            print("${this[i][j].isVible} ")
        }
        println()
    }
}

fun getColumn(matrix :List<List<Tree>>, col: Int): MutableList<Tree> {
    val nums= mutableListOf<Tree>()
    for (i in matrix.indices) {
        nums[i] = matrix[i][col]
    }
    return nums
}




fun orderedNeighborsInMatrix(width: Int, height: Int, x: Int, y: Int): List<Pair<Int, Int>> {
    val myList = mutableListOf<Pair<Int, Int>>()
    if (x + 1 < width - 1) myList += Pair(x + 1, y)
    //if (x + 1 < width - 1 && y + 1 < height - 1) myList += Pair(x + 1, y + 1)
    if (y + 1 < height - 1) myList += Pair(x, y + 1)
    // if (x - 1 > 0 && y + 1 < height - 1) myList += Pair(x - 1, y + 1)
    if (x - 1 > 0) myList += Pair(x - 1, y)
    //if (x - 1 > 0 && y - 1 > 0) myList += Pair(x - 1, y - 1)
    if (y - 1 > 0) myList += Pair(x, y - 1)
    //if (x + 1 < width - 1 && y - 1 > 0) myList += Pair(x + 1, y - 1)
    return myList
}