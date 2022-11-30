package advent2022


import java.util.*
import kotlin.io.path.Path

fun readIntList(path: String): MutableList<Int> {
    val scanner = Scanner(Path(path));

    val list = mutableListOf<Int>()
    while (scanner.hasNext()) {
        val number = scanner.nextInt()
        list += number
    }
    return list
}