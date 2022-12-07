package advent2022

import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val lines = Path("src/main/resources/2022day7_s.txt").readText().split("\n").map { it.split(" ") }
    var r= lines.minOf { it[1].toInt() }

    val totalSpace = 70000000
    val updateSpace = 30000000
    //val totalUsedSapce = 48381165
    val totalUsedSapce = 42476859
    val unused = totalSpace -totalUsedSapce
    val sizeToDelete = updateSpace -unused
    println(r)
}