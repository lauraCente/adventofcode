package advent2022.utils

class Point(val x: Int, val y: Int) {

    fun linearNeighbors(): List<Point> =
        listOf(
            Point(x, y + 1),
            Point(x, y - 1),
            Point(x + 1, y),
            Point(x - 1, y)
        )

    fun diagonalNeighbors(): List<Point> =
        listOf(
            Point(x - 1, y - 1),
            Point(x - 1, y + 1),
            Point(x + 1, y - 1),
            Point(x + 1, y + 1)
        )

    fun orderedNeighborsInMatrix(width: Int, height: Int): List<Point> {
        val myList = mutableListOf<Point>()
        if (x + 1 < width - 1) myList += Point(x + 1, y)
        if (x + 1 < width - 1 && y + 1 < height - 1) myList += Point(x + 1, y + 1)
        if (y + 1 < height - 1) myList += Point(x, y + 1)
        if (x - 1 > 0 && y + 1 < height - 1) myList += Point(x - 1, y + 1)
        if (x - 1 > 0) myList += Point(x - 1, y)
        if (x - 1 > 0 && y - 1 > 0) myList += Point(x - 1, y - 1)
        if (y - 1 > 0) myList += Point(x, y - 1)
        if (x + 1 < width - 1 && y - 1 > 0) myList += Point(x + 1, y - 1)
        return myList
    }

    fun allNeighbors(): List<Point> = linearNeighbors() + diagonalNeighbors()


}