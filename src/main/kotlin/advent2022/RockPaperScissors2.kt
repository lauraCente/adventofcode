package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText

enum class RockPaperScissorsP1(val value: Int) {
    A(1), //Rock
    B(2), //Paper
    C(3), //Scissors
}

enum class RockPaperScissorsP2(val value: Int) {
    X(1), //Rock //lose
    Y(2), //Paper //draw
    Z(3) //Scissors //win
}

enum class OutcomeRound(val value: Int) {
    LOST(0),
    DRAW(3),
    WON(6)
}

fun main() {
    val list = Path("src/main/resources/day2_1.txt").readText().split("\n").map { it.split(" ") }

    val result1 = part1(list)
    println("part1 = $result1")

    val result2 = part2(list)
    println("part2 = $result2")
}

fun part2(list: List<List<String>>): Int {
    val myShapes = mutableListOf<String>()
    for (i in list.indices) {
        myShapes += chooseShape(list[i][0], list[i][1])
    }

    val score = mutableListOf<Int>()
    for (i in list.indices) {
        score += shapeSelectedScorePart2(myShapes[i]) + outcomeScore(
            RockPaperScissorsP1.valueOf(list[i][0]).value,
            RockPaperScissorsP1.valueOf(myShapes[i]).value
        )
    }
    return score.sum()
}

//A(1), //Rock
//    X(1),  //lose scissors
//    Y(2),  //draw rock
//    Z(3)  //win paper
//B(2), //Paper
//    X(1),  //lose rock
//    Y(2),  //draw paper
//    Z(3)  //win scissors
//C(3), //Scissors
//    X(1), //lose paper
//    Y(2),  //draw scissors
//    Z(3)  //win rock
fun chooseShape(p1: String, p2: String): String {
    val player1 = RockPaperScissorsP1.valueOf(p1)
    val player2 = RockPaperScissorsP2.valueOf(p2)

    val shape: RockPaperScissorsP1
    when (player1) {
        RockPaperScissorsP1.A -> {
            when (player2) {
                RockPaperScissorsP2.X -> shape = RockPaperScissorsP1.C
                RockPaperScissorsP2.Y -> shape = RockPaperScissorsP1.A
                RockPaperScissorsP2.Z -> shape = RockPaperScissorsP1.B
            }
        }
        RockPaperScissorsP1.B -> {
            when (player2) {
                RockPaperScissorsP2.X -> shape = RockPaperScissorsP1.A
                RockPaperScissorsP2.Y -> shape = RockPaperScissorsP1.B
                RockPaperScissorsP2.Z -> shape = RockPaperScissorsP1.C
            }
        }
        RockPaperScissorsP1.C -> {
            when (player2) {
                RockPaperScissorsP2.X -> shape = RockPaperScissorsP1.B
                RockPaperScissorsP2.Y -> shape = RockPaperScissorsP1.C
                RockPaperScissorsP2.Z -> shape = RockPaperScissorsP1.A
            }
        }
    }
    return shape.name
}

fun part1(list: List<List<String>>): Any {
    val score = mutableListOf<Int>()
    for (i in list.indices) {
        score += shapeSelectedScore(list[i][1]) + outcomeScore(
            RockPaperScissorsP1.valueOf(list[i][0]).value,
            RockPaperScissorsP2.valueOf(list[i][1]).value
        )
    }
    return score.sum()
}


fun outcomeScore(player1: Int, player2: Int): Int {
    val score = when (player1 - player2) {
        0 -> OutcomeRound.DRAW.value
        -1, 2 -> OutcomeRound.WON.value
        else -> OutcomeRound.LOST.value //-2 && 1
    }
    return score
}

//        A(1), //Rock
//            Y(2), //Paper win -1
//            Z(3) //Scissors lost -2
//        B(2), //Paper
//            X(1), //Rock lost 1
//            Z(3) //Scissors win -1
//        C(3), //Scissors
//            X(1), //Rock win 2
//            Y(2), //Paper lost 1
//
//win -1 && 2
//lost -2 && 1

fun shapeSelectedScore(player2: String): Int {
    return RockPaperScissorsP2.valueOf(player2).value
}

fun shapeSelectedScorePart2(player2: String): Int {
    return RockPaperScissorsP1.valueOf(player2).value
}
