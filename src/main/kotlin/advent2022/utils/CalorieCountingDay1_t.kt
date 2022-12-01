import advent2022.readAllLinesFromScanner
import advent2022.utils.printMatrix
import advent2022.utils.subListByElement
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val scanner = Scanner(Path("src/main/resources/day1_t.txt"))

    val caloriesByElf = getCaloriesByElf2(scanner).map { it.map { it.toInt() } }
    val sumCalByElf = caloriesByElf.map { it.sumOf { it }  }.sorted().reversed()

    println("result1 = $sumCalByElf[0] ")
    println("result2 = ${sumCalByElf[0]+sumCalByElf[1]+sumCalByElf[2]}")
}

fun getCaloriesByElf2(scanner: Scanner): MutableList<MutableList<String>> {
    val elfCalories=readAllLinesFromScanner(scanner)

    val elfByCalories = elfCalories.subListByElement("")
    return elfByCalories
}

