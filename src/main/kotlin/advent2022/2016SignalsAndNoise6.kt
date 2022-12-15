package advent2022


import kotlin.io.path.Path
import kotlin.io.path.readText

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val puzzle = Path("src/main/resources/2022day6_1.txt").readText().split("\n").map { it.toList() }
    println(puzzle)

    var word=""
    for(column in puzzle[0].indices){
        val myMap = mutableMapOf<Char,Int>()
        for (i in puzzle.indices){
            val line = puzzle[i]
            val letter = line[column]
            if (myMap.containsKey(letter)){
                val occ= myMap.getValue(letter)
                myMap[letter]= occ +1
            } else {
                myMap.put(letter,1)
            }
        }
        //word += myMap.toList().maxByOrNull { (k,v) -> v  }?.first ?: ""
        word += myMap.toList().minByOrNull { (_,v) -> v  }?.first ?: ""
    }

    println(word)
}


