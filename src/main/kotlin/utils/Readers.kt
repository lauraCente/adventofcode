package advent2022


import java.util.*
import kotlin.io.path.Path

/*
Llegeix ints -> llista
Exemple
199
200
208
 */
fun readIntsList(path: String): MutableList<Int> {
    val scanner = Scanner(Path(path))

    val list = mutableListOf<Int>()
    while (scanner.hasNext()) {
        val number = scanner.nextInt()
        list += number
    }
    return list
}

/**
 * read numbers and split into matrix of digits
 * 00100
 * 11110
 * 10110
 */
fun readNumbersToMatrix(path:String): MutableList<MutableList<Int>>{
    val scanner = Scanner(Path(path))

    val matrix = mutableListOf<MutableList<Int>>()
    while (scanner.hasNext()) {
        val lineNumbers = scanner.nextLine().map{it.toString().toInt()}.toMutableList()
        matrix +=lineNumbers
    }
    return matrix
}

/**
 * Read lines and split into matrix of digits
 */
fun readLineToMatrix(path:String, delimiterSplit: String): MutableList<MutableList<Int>>{
    val scanner = Scanner(Path(path))

    val matrix = mutableListOf<MutableList<Int>>()
    while (scanner.hasNext()) {
        val numbers = scanner.nextLine().split(delimiterSplit)
        val lineNumbers = numbers.map { it.toInt() }.toMutableList()
        matrix +=lineNumbers
    }
    return matrix
}

/*
Read int to int and built matrix i lines j elements per line
 */
fun readIntsToMatrix(path:String, i:Int, j:Int): MutableList<MutableList<Int>>{
    val scanner = Scanner(Path(path))
    val matrix = MutableList(i){MutableList(j){scanner.nextInt()}}
    return matrix
}
fun readAllLinesFromScanner(scanner: Scanner): MutableList<String> {
    val list = mutableListOf<String>()
    while (scanner.hasNext()){
        list+=scanner.nextLine()
    }
    return list
}