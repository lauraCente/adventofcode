package advent2023

import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    var lines = Path("src/main/resources/2023day1_2.txt").readText().split("\n")
    var total = 0

    for (line in lines) {
        var firstDigit: Int = getFirstDigit(line.toList())
        //var firstDigit = line.firstOrNull { c: Char -> c.isDigit() }
        var lastDigit = getLastDigit(line.toList())
        //var lastDigit = line.lastOrNull { c: Char -> c.isDigit() }
        var lineSum = firstDigit * 10 + lastDigit
        println(lineSum)
        //var lineSum = (firstDigit?.digitToInt() ?: 0) * 10 + (lastDigit?.digitToInt() ?: 0)
        total = total + lineSum
    }
    println(total)
}


fun getFirstDigit(line: List<Char>): Int {
    var myDigit: Int = 0
    var found = false
    for (i in line.indices) {
        if (line[i].isDigit()) {
            myDigit = line[i].digitToIntOrNull()!!
            break
        }
        if (line[i] == 'o') {
            if (i + 2 <= line.lastIndex && line[i + 1] == 'n' && line[i + 2] == 'e') {
                myDigit = 1
                break
            }
        }
        if (line[i] == 't') {
            if (i + 2 <= line.lastIndex && line[i + 1] == 'w' && line[i + 2] == 'o') {
                myDigit = 2
                break
            } else if (i + 4 <= line.lastIndex && line[i + 1] == 'h' && line[i + 2] == 'r' && line[i + 3] == 'e' && line[i + 3] == 'e') {
                myDigit = 3
                break
            }
        }
        if (line[i] == 'f') {
            if (i + 3 <= line.lastIndex && line[i + 1] == 'o' && line[i + 2] == 'u' && line[i + 3] == 'r') {
                myDigit = 4
                break
            } else if (i + 3 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'v' && line[i + 3] == 'e') {
                myDigit = 5
                break
            }
        }
        if (line[i] == 's') {
            if (i + 2 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'x') {
                myDigit = 6
                break
            } else if (i + 4 <= line.lastIndex && line[i + 1] == 'e' && line[i + 2] == 'v' && line[i + 3] == 'e' && line[i + 4] == 'n') {
                myDigit = 7
                break
            }
        }

        if (line[i] == 'e') {
            if (i + 4 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'g' && line[i + 3] == 'h' && line[i + 4] == 't') {
                myDigit = 8
                break
            }
        }
        if (line[i] == 'n') {
            if (i + 3 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'n' && line[i + 3] == 'e') {
                myDigit = 9
                break
            }
        }
        if (line[i] == 'z') {
            if (i + 3 <= line.lastIndex && line[i + 1] == 'e' && line[i + 2] == 'r' && line[i + 3] == 'o') {
                myDigit = 0
                break
            }
        }
    }
    return myDigit
}


fun getLastDigit(line: List<Char>): Int {
    var myDigit: Int = 0
    var found = false
    for (i in line.lastIndex downTo 0) {
        if (line[i].isDigit()) {
            myDigit = line[i].digitToIntOrNull()!!
            break
        }
        if (line[i] == 'o') {
            if (i + 2 <= line.lastIndex && line[i + 1] == 'n' && line[i + 2] == 'e') {
                myDigit = 1
                break
            }
        }
        if (line[i] == 't') {
            if (i + 2 <= line.lastIndex && line[i + 1] == 'w' && line[i + 2] == 'o') {
                myDigit = 2
                break
            }
            else if (i + 4 <= line.lastIndex && line[i + 1] == 'h' && line[i + 2] == 'r' && line[i + 3] == 'e' && line[i + 3] == 'e') {
                myDigit = 3
                break
            }
        }
        if (line[i] == 'f') {
            if (i + 3 <= line.lastIndex && line[i + 1] == 'o' && line[i + 2] == 'u' && line[i + 3] == 'r') {
                myDigit = 4
                break
            }
            else if (i + 3 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'v' && line[i + 3] == 'e') {
                myDigit = 5
                break
            }
        }
        if (line[i] == 's') {
            if (i + 2 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'x'){
                myDigit = 6
                break
            }
            else if (i + 4 <= line.lastIndex && line[i + 1] == 'e' && line[i + 2] == 'v' && line[i + 3] == 'e' && line[i + 4] == 'n') {
                myDigit = 7
                break
            }
        }
        if (line[i] == 'e') {
            if (i + 4 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'g' && line[i + 3] == 'h' && line[i + 4] == 't') {
                myDigit = 8
                break
            }
        }
        if (line[i] == 'n') {
            if (i + 3 <= line.lastIndex && line[i + 1] == 'i' && line[i + 2] == 'n' && line[i + 3] == 'e') {
                myDigit = 9
                break
            }
        }
        if (line[i] == 'z') {
            if (i + 3 <= line.lastIndex && line[i + 1] == 'e' && line[i + 2] == 'r' && line[i + 3] == 'o') {
                myDigit = 0
                break
            }
        }
    }
    return myDigit
}



