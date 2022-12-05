import java.util.*
import kotlin.io.path.Path

fun main() {
    val scanner = Scanner(Path("src/main/resources/2022day5_t.txt"))

    val lines = mutableListOf<List<String>>()
    val maxProduct = 3 //8
    repeat(maxProduct){
         lines += scanner.nextLine().chunked(4)
    }
    val crates = List<MutableList<String>>(lines[maxProduct-1].size){ mutableListOf("")}
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




}