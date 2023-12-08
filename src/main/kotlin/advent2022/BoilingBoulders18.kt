package advent2022

import advent2022.utils.Point3D
import kotlin.io.path.Path
import kotlin.io.path.readText


fun main() {
    val puzzle = Path("src/main/resources/2022day18_t.txt").readText().split("\n")
    var points: MutableList<Point3D> = readPoints(puzzle)

    var boulder = mutableListOf<Point3D>()
    for (point in points){
        if(boulder.isEmpty()) boulder.add(point)
        else {
            for (element in boulder){
                element.addAdjacent(point)
            }
            boulder.add(point)
        }
    }
    var result = boulder.sumOf { it.exposedSides }
    boulder.forEach { println(it)
                        println(it.exposedSides)}
    println(result)
}


/**
 * puzzle:
 * 2,2,2
 * 1,2,2
 */
fun readPoints(puzzle: List<String>): MutableList<Point3D> {
    var points = mutableListOf<Point3D>()
    for (line in puzzle) {
        var (x, y, z) = line.split(",")
        points += Point3D(x.toInt(), y.toInt(), z.toInt())
    }
    return points
}
