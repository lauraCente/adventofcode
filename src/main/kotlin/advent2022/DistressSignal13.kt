package advent2022

import kotlin.io.path.Path
import kotlin.io.path.readText

class Packet(val name: String, val parent: Packet?) : Comparable<Packet> {

    val elements = mutableListOf<Packet>()
    val value: Int? = if (name != "[" && name != "]") name.toInt() else null
    var isFinal = false
    val hasElements get() = this.elements.size > 0

    fun add(packet: Packet): Packet {
        elements += packet
        if (packet.name == "[") return packet
        else if (packet.name == "]") {
            return packet.parent!!
        } else return this
    }

    fun final() {
        this.isFinal = true
    }

    override fun compareTo(other: Packet): Int {
        if (this.isFinal && other.isFinal) {
            return this.value!!.compareTo(other.value!!)

        } else if (!this.isFinal && !other.isFinal) {
            this.elements.forEachIndexed { i, elem ->
                if (i in other.elements.indices) {
                    val compare = elem.compareTo(other.elements[i])
                    if (compare != 0) return compare
                } else {
                    return 1
                }
            }
            if (other.elements.size > this.elements.size) return -1
            else return 0
        } else if (this.isFinal && !other.isFinal) {
            val packet = Packet("[", null)
            packet.add(this)
            return packet.compareTo(other)
        } else {
            //!this.isFinal && other.isFinal
            val packet = Packet("[", null)
            packet.add(other)
            return this.compareTo(packet)
        }
    }

    override fun toString(): String {
        var s = this.name
        this.elements.forEach {
            s += it.toString()
        }
        return s
    }

     fun equals(other: Packet): Boolean {
        val b = this.name == other.name
         val elements =  this.elements.size == other.elements.size
         if (b && elements){
             this.elements.forEachIndexed() { i, elem ->
                 if (elem.name != other.elements[i].name) return false
             }
             return true
         }
         else{
             return false
         }
    }

}

fun createPacket(line: String): Packet {
    val root = Packet(line[0].toString(), null)
    var packet = root
    var i = 1
    while (i in 1..line.lastIndex) {
        var element = line[i].toString()
        if (element == "1" && line[i + 1].toString() == "0") {
            element = "10"
            i++
        }
        when (element) {
            "[" -> packet = packet.add(Packet(element, packet))
            "]" -> packet = if (packet.parent != null) packet.parent!! else packet
            "," -> {
                i++
                continue
            }
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> {
                val packetFinal = Packet(element, packet)
                packetFinal.final()
                packet.add(packetFinal)
            }
            else -> println("ERROR CREATE PACKET")
        }
        i++
    }
    return root
}


fun main() {
    val lines = Path("src/main/resources/2022day13_1.txt").readText().split("\n")

//    val rightOrder = part1Day13(lines)
//    println(rightOrder)
    val packets = mutableListOf<Packet>()
    for (i in lines.indices step 3) {
        var line = lines[i]
        val packetA: Packet = createPacket(line)
        packets+=packetA
        line = lines[i+1]
        val packetB: Packet = createPacket(line)
        packets+=packetB
    }
    val packet2 = createPacket("[[2]]")
    packets.add(packet2)
    val packet6 = createPacket("[[6]]")
    packets.add(packet6)
    packets.sort()
    println(packets.indexOf(packet2)+1)
    println(packets.indexOf(packet6)+1)





}

fun part1Day13(lines: List<String>): Int {
    var rightOrder = 0
    var pair = 0
    for (i in lines.indices step 3) {
        var line = lines[i]
        val packetA: Packet = createPacket(line)
        line = lines[i + 1]
        val packetB: Packet = createPacket(line)
        pair++
        if (packetA.compareTo(packetB) < 0) {
            rightOrder += pair
            println("line in rightOrder ${pair}")
        }
    }
    return rightOrder
}


