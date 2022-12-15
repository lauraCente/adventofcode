package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.max
import kotlin.math.min

val matrix = List<MutableList<Int>>(180) { MutableList<Int>(525) { 0 } }
fun main() {

    val lines = Path("src/main/resources/2022day14_1.txt").readText().split("\n")
        .map { it.split("->").map { it.split(",").map { it.trim().toInt() } } }

    for (line in lines){
        var pointA = line[0]
        var pointB : List<Int>
        for (i in 1..line.lastIndex) {
            pointB = line[i]
            drawLine(pointA, pointB)
            pointA = pointB
        }
    }

    matrix[0][1] = 1
    matrix.forEach {
        it.forEach{ print(it)}
        println()
    }

}

fun drawLine(pointA: List<Int>, pointB: List<Int>) {
    if (pointA[0] == pointB[0]){
        val smallRange = min(pointA[1], pointB[1])
        val bigRange = max(pointA[1], pointB[1])
        for (i in smallRange..bigRange ){
            matrix[i][pointA[0]] = 1
        }
    } else{
        val smallRange = min(pointA[0], pointB[0])
        val bigRange = max(pointA[0], pointB[0])
        for (i in smallRange..bigRange ){
            matrix[pointA[1]][i] = 1
        }
    }
}
