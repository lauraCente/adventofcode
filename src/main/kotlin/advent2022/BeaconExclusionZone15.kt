package advent2022

import advent2022.utils.printMatrix
import java.lang.Math.*
import kotlin.io.path.Path
import kotlin.io.path.readText

data class Sensor(val x: Int, val y: Int, val beacon: Beacon) {
    var manhattan = abs(beacon.x - x) + abs(beacon.y - y)
}

class Beacon(val x: Int, val y: Int) {

}

private val movedMatrix = 8000000

private val row = 2000000
//private val row = 10

//private val matrixBeacon = List<MutableList<Int>>(24) { MutableList<Int>(31) { 0 } }
//private val matrixBeacon = List<MutableList<Int>>(24) { MutableList<Int>(31) { 0 } }
//private val rowBeacon = MutableList<Int>(31) { 0 }
val rowBeacon = MutableList<Int>(8000000 *2) {   0  }
fun main() {
    val text = Path("src/main/resources/2022day15_1.txt").readText()
    var lines = text.split("\n").map {
        it.split("Sensor at ").filter { it != "" }.map { it.split(": closest beacon is at ").filter { it != "" } }
    }

    var sensors: MutableList<Sensor> = getListOfSensors(lines)

    for (sensor in sensors) {
        if (sensor.y == row) rowBeacon[sensor.x] = 2
        if (sensor.beacon.y == row) rowBeacon[sensor.beacon.x] = 3
        calculateCoveredZone(sensor)
    }
    //matrixBeacon.printMatrix()

    var covered = getCoveredPointsInRow(row)
    println(covered)

}

fun getCoveredPointsInRow(fixedRow: Int): Int {
    var covered = 0
    for (i in 0..rowBeacon.lastIndex) {
        if (rowBeacon[i] == 1) {
            covered++
        }
    }
    return covered
}

fun getListOfSensors(lines: List<List<List<String>>>): MutableList<Sensor> {
    var sensors = mutableListOf<Sensor>()
    for (line in lines) {
        var beacon = createBeacon(line[0][1])
        sensors += createSensor(line[0][0], beacon)
    }
    return sensors
}

fun calculateCoveredZone(sensor: Sensor) {
    val x = sensor.x
    val y = sensor.y
//    val rang2Y = min(y + sensor.manhattan, matrixBeacon.lastIndex)
//    val rang1Y = max(y - sensor.manhattan, 0)
    val rang2X = min(x + sensor.manhattan, rowBeacon.lastIndex)
    val rang1X = max(x - sensor.manhattan, 0)
    for (i in row..row) {
        for (j in rang1X..rang2X) {
            var dist = abs(y - i) + abs(x - j)
            if (rowBeacon[j] == 0 && dist <= sensor.manhattan) {
                rowBeacon[j] = 1
            }
        }
    }
}

fun createSensor(text: String, beacon: Beacon): Sensor {
    val point: Pair<Int, Int> = textToPoint(text)
    return Sensor(point.first, point.second, beacon)
}


fun createBeacon(text: String): Beacon {
    val point: Pair<Int, Int> = textToPoint(text)
    return Beacon(point.first, point.second)
}

//x=2, y=18
fun textToPoint(text: String): Pair<Int, Int> {
    var elems = text.split(", ").map { it.split("x=").filter { it != "" }.map { it.split("y=").filter { it != "" } } }
    var x = elems[0][0][0].toInt() + movedMatrix
    var y = elems[1][0][0].toInt()
    return Pair(x, y)
}
