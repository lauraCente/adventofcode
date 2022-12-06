package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val puzzle = Path("src/main/resources/2022day6_1.txt").readText()
    val chunk = 4 // part 2-> 14
    puzzle.windowed(chunk).forEachIndexed { index, s ->
        if (s.toList().toSet().size == chunk){
            println(index+chunk)
            return
        }
    }

}