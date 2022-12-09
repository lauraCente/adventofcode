package advent2022.v2

import kotlin.io.path.Path
import kotlin.io.path.readText


class Tree(val height: Int, var isVible: Boolean = false, var scenicScore: Int = 1)

fun main() {
    val text = Path("src/main/resources/2022day8_1.txt").readText()
    val matrix = text.split("\n").map { it.split("").filter { it != "" }.map { Tree(it.toInt()) } }
//    val width = matrix[0].size
//    val height = matrix.size

    matrix.printMatrixHeight()

    println()
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            calculateScenicScore(matrix[i][j], i, j, matrix)
        }
    }

    var max =matrix[0][0].scenicScore
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if( matrix[i][j].scenicScore > max ) max = matrix[i][j].scenicScore
        }
    }
    matrix.printMatrixSc()
    println()
    println(max)




}

fun calculateScenicScore(tree: Tree, i: Int, j: Int, matrix: List<List<Tree>>) {
    val upListTrees = getUpListToEdge(matrix, i, j, tree)
    val downListTrees = getDownListToEdge(matrix, i, j, tree)
    val leftTrees = getLeftListToEdge(matrix, i, j, tree)
    val rightTrees = getRightListToEdge(matrix, i, j, tree)
    tree.scenicScore=upListTrees*downListTrees*leftTrees*rightTrees
}

fun getLeftListToEdge(matrix: List<List<Tree>>, x: Int, y: Int, tree: Tree): Int {
    var count = 0
    var edgePoint =true
    for (i in y - 1 downTo 0) {
        if (tree.height > matrix[x][i].height) count++
        else {
            edgePoint = false
            break
        }
    }
    if (!edgePoint) count++
    return count
}
fun getRightListToEdge(matrix: List<List<Tree>>, x: Int, y: Int, tree: Tree): Int {
    var count = 0
    var edgePoint =true
    for (i in y + 1..matrix[0].size-1) {
        if (tree.height > matrix[x][i].height) count++
        else {
            edgePoint = false
            break
        }
    }
    if (!edgePoint) count++
    return count
}

fun getUpListToEdge(matrix: List<List<Tree>>, x: Int, y: Int, tree: Tree): Int {
    var count = 0
    var edgePoint =true
    for (i in x - 1 downTo 0) {
        if (tree.height > matrix[i][y].height) count++
        else {
            edgePoint = false
            break
        }
    }
    if (!edgePoint) count++
    return count
}

fun getDownListToEdge(matrix: List<List<Tree>>, x: Int, y: Int, tree: Tree): Int {
    var count = 0
    var edgePoint =true
    for (i in x+1 .. matrix.size-1) {
        if (tree.height > matrix[i][y].height) count++
        else {
            edgePoint = false
            break
        }
    }
    if (!edgePoint) count++
    return count
}

fun visibles(fila: List<Tree>): Int {
    var maxHeight = fila[0].height
    var countVisibles: Int
    if (!fila[0].isVible) {
        countVisibles = 1
        fila[0].isVible = true
    } else countVisibles = 0

    for (i in 1..fila.lastIndex) {
        if (fila[i].height > maxHeight) {
            maxHeight = fila[i].height
            if (!fila[i].isVible) {
                countVisibles++
                fila[i].isVible = true
            }
        }
    }
    return countVisibles
}


fun List<List<Tree>>.printMatrixHeight() {
    for (i in this.indices) {
        for (j in this[i].indices) {
            print("${this[i][j].height} ")
        }
        println()
    }
}

fun List<List<Tree>>.printMatrixVisible() {
    for (i in this.indices) {
        for (j in this[i].indices) {
            print("${this[i][j].isVible} ")
        }
        println()
    }
}

fun List<List<Tree>>.printMatrixSc() {
    for (i in this.indices) {
        for (j in this[i].indices) {
            print("${this[i][j].scenicScore} ")
        }
        println()
    }
}


/**
 *
30373
25512
65332
33549
35390

0 0 0 0 0
 0 1
 */

