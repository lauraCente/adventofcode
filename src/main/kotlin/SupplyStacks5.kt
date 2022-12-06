import java.util.*
import kotlin.io.path.Path

fun main() {
    val scanner = Scanner(Path("src/main/resources/2022day5_1.txt"))

    val lines = mutableListOf<List<String>>()
    val maxProduct = 8 //3
    repeat(maxProduct){
         lines += scanner.nextLine().chunked(4)
    }
    val crates = MutableList <MutableList<String>>(lines[maxProduct-1].size){ mutableListOf("")}
    for (i in maxProduct-1 downTo 0) {
        val cleanedLine = lines[i].map { line -> line.replace("[", "").replace("]", "") }
        for (j in cleanedLine.indices){
            if (cleanedLine[j] != "    "){
                if (crates[j][0] == "" ) crates[j][0]=cleanedLine[j]
                else crates[j]+=cleanedLine[j]
            }
        }
    }
    println(crates)

    scanner.nextLine()
    scanner.nextLine()
//    while (scanner.hasNext()){
//        val mov = scanner.nextLine().split(" ")
//        val times = mov[1].toInt()
//        val from = mov[3].toInt()
//        val to = mov[5].toInt()
//        repeat(times){
//            crates[to-1] += crates[from-1].removeLast()
//        }
//    }

    while (scanner.hasNext()){
        val mov = scanner.nextLine().split(" ")
        val times = mov[1].toInt()
        val from = mov[3].toInt()
        val to = mov[5].toInt()

        val elementsToMove = crates[from-1].subList(crates[from-1].size-times, crates[from-1].size).toList()
        repeat(elementsToMove.size) {
            crates[from-1].removeLast()
        }
        crates[to-1] += elementsToMove
    }
    println(crates)

    var result = ""
    for (i in crates.indices){
        val letter=crates[i].removeLast().toString()
        if(letter!=" ") result+=letter

    }
    println(result.replace(" ",""))



}