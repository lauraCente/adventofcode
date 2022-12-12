package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText


private var x = 0
private var cycle = 0
private var CRT = MutableList(6){ mutableListOf<Boolean>()}
private var line = 0

var signalsStrengths = mutableListOf<Int>()
fun main() {

    val instruccions = Path("src/main/resources/2022day10_1.txt").readText().split("\n").map { it.split(" ") }
    initVars()
    for (instruccion in instruccions) {
        if (instruccion[0] == "addx") {
            addx(instruccion[1].toInt())
        } else if (instruccion[0] == "noop") {
            noop()
        }
    }
    //part1
//    var total =signalsStrengths.sumOf {it}
//    println(signalsStrengths)
//    println(total)
    printCRT()
}

fun printCRT() {
    for (i in CRT.indices){
        for(j in CRT[i].indices){
            if (CRT[i][j]) print("#")
            else print(".")
        }
        println()
    }
}

fun initVars() {
    x++
    //addCRT()
}

fun addCRT() {
    if (cycle%40 == x || cycle%40 == x+1 || cycle%40 == x+2){
        CRT[line].add(true)
    }
    else CRT[line].add(false)
}

fun addx(number: Int) {
    incrementCycle()
    incrementCycle()
    x = x + number
}

fun noop() {
    incrementCycle()
}

fun incrementCycle() {
    cycle++
    addCRT()
    checkSignalStrengths()
}

fun checkSignalStrengths() {
    if (cycle == 40 || cycle == 80 || cycle == 120 || cycle == 160 || cycle == 200 ) {
        signalsStrengths+= cycle*x
        line++
    }
}
