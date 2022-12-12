package advent2022

import java.util.*
import java.util.function.BinaryOperator
import java.util.function.LongBinaryOperator
import kotlin.io.path.Path

enum class LongArithmetics : BinaryOperator<Long>, LongBinaryOperator {
    PLUS {
        override fun apply(t: Long, u: Long): Long = t + u
    },
    TIMES {
        override fun apply(t: Long, u: Long): Long = t * u
    };

    override fun applyAsLong(t: Long, u: Long) = apply(t, u)
}

data class Monkey(var worriedPoints: Long?, var test: Long, var t: Int, var f: Int, var operation: LongArithmetics) {
    var items = mutableListOf<Long>()
    var inspectedItems =0L

    fun addItem(item: Long) {
        items += item
    }

    fun addItems(items: List<Long>) {
        this.items += items
    }

    override fun toString(): String {
        return inspectedItems.toString()
    }

    fun removeItems() {
        items.clear()
    }
}

fun main() {
    val scanner = Scanner(Path("src/main/resources/2022day11_1.txt"))

    val monkeys = mutableListOf<Monkey>()
    while (scanner.hasNext()) {
        val line = scanner.nextLine()

        if (line.contains("Monkey")) {
            monkeys += readMonkey(scanner)
        }
        if (scanner.hasNext()) scanner.nextLine()
    }
    println(monkeys)
   // var factor = 23*19*13*17
    val factor = 13*7*19*2*5*3*11*17
    repeat(10000) {
        for (monkey in monkeys) {
            for (item in monkey.items) {
                var worriedPoints = monkey.worriedPoints
                if (monkey.worriedPoints == null) {
                    worriedPoints = item
                }
                val worry:Long = (monkey.operation.apply(item, worriedPoints!!))%factor
                if (worry % monkey.test == 0L){
                    monkeys[monkey.t].addItem(worry)
                }
                else {
                    monkeys[monkey.f].addItem(worry)
                }
                monkey.inspectedItems++
            }
            monkey.removeItems()
        }
    }
    println(monkeys)
    val final =monkeys.sortedBy { it.inspectedItems }.reversed().take(2)
    println(final[0].inspectedItems*final[1].inspectedItems)
}

fun readMonkey(scanner: Scanner): Monkey {
    val line = scanner.nextLine().split("Starting items:").map { it.trim() }.filter { it != "" }
    val items = line[0].split(",").map { it.trim().toLong() }

    val worriedPointsList = scanner.nextLine().split("Operation: new = old ").filter { it != "  " }

    val worriedStuff = worriedPointsList[0].split(" ")
    val operation = if (worriedStuff[0] == "*") LongArithmetics.TIMES else LongArithmetics.PLUS //detectar operation

    val worriedPoints = if (worriedStuff[1] == "old") null else worriedStuff[1].toLong() //parsejar si numero

    val testList = scanner.nextLine().split("Test: divisible by ").filter { it != "  " }
    val test = testList[0].toLong()

    val ifTrueList = scanner.nextLine().split("If true: throw to monkey ").filter { it != "  " }
    val ifTrue = ifTrueList[1].toInt()

    val ifFalseList = scanner.nextLine().split("If false: throw to monkey ").filter { it != "  " }
    val ifFalse = ifFalseList[1].toInt()

    val monkey = Monkey(worriedPoints, test, ifTrue, ifFalse, operation)
    monkey.addItems(items)
    return monkey
}


