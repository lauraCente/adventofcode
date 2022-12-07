package advent2022

import advent2022.utils.TreeNode
import java.util.*
import kotlin.io.path.Path

fun main() {
    val scanner = Scanner(Path("src/main/resources/2022day7_1.txt"))

    val root = getTreeNodeFromEntry(scanner)

    val result: Int = calculatePart1(root)
    println(result)

    val totalSpace = 70000000
    val updateSpace = 30000000
    val totalUsedSpace = sumInDepth(root)
    val unused = totalSpace - totalUsedSpace
    val sizeToDelete = updateSpace - unused

    val result2 = calculatePart2(root, sizeToDelete)
    println(result2)


}

fun calculatePart2(root: TreeNode<Pair<String, Int>>, size: Int): Int {
    val sums = mutableListOf<Pair<String, Int>>()
    root.forEachDepthFirst {
        val sum :Int
        if (it.data.second == 0) {
            sum = sumInDepth(it)
            if (sum > size) {
                //println("${it.data.first} ${sum}")
                sums += Pair(it.data.first, sum)
            }
        }
    }
    val result = sums.minOf { (_, i) -> i }
    return result
}

fun calculatePart1(root: TreeNode<Pair<String, Int>>): Int {
    val sums = mutableListOf<Pair<String, Int>>()
    root.forEachDepthFirst {
        val sum: Int
        if (it.data.second == 0) {
            sum = sumInDepth(it)
            if (sum < 100000) {
                //println("${it.data.first} ${sum}")
                sums += Pair(it.data.first, sum)
            }
        }
    }
    val result: Int = sums.sumOf { (_, i) -> i }
    return result
}

fun sumInDepth(currentElement: TreeNode<Pair<String, Int>>): Int {
    val size: Int
    if (currentElement.children.size == 0) size = 0
    else {
        val currentSize = currentElement.data.second
        var s = 0
        for (element in currentElement.children) {
            s += sumInDepth(element) + element.data.second
        }
        size = currentSize + s
    }
    return size
}

fun getTreeNodeFromEntry(scanner: Scanner): TreeNode<Pair<String, Int>> {

    val root = TreeNode<Pair<String, Int>>(Pair("/", 0))
    var currentElement = root
    var pair: Pair<TreeNode<Pair<String, Int>>, String>
    var text = scanner.nextLine()
    while (text != "") {
        val line = text.split(" ")
        if (line[0] == "\$") {
            pair = executeCommand(line, currentElement, scanner)
            text = pair.second
            currentElement = pair.first
        }
    }
    return root
}

fun executeCommand(
    command: List<String>,
    currentElement: TreeNode<Pair<String, Int>>,
    scanner: Scanner
): Pair<TreeNode<Pair<String, Int>>, String> {
    var pair = Pair(currentElement, "")
    if (command[1] == "ls") pair = Pair(currentElement, executeCreateDirectories(currentElement, scanner))
    if (command[1] == "cd") {
        pair = moveIntoDirectory(currentElement, command[2], scanner)
    }
    return pair
}

fun moveIntoDirectory(
    currentElement: TreeNode<Pair<String, Int>>, directoryToMove: String, scanner: Scanner
): Pair<TreeNode<Pair<String, Int>>, String> {
    var elementToMove = currentElement
    if (directoryToMove == "..") {
        elementToMove = currentElement.parent!!
    }
    for (child in currentElement.children) {
        if (child.data.first == directoryToMove) {
            elementToMove = child
            break
        }
    }
    var line = ""
    if (scanner.hasNext()) line = scanner.nextLine()
    return Pair(elementToMove, line)
}

fun executeCreateDirectories(currentElement: TreeNode<Pair<String, Int>>, scanner: Scanner): String {
    var line = scanner.nextLine()
    while (line != "") {
        val content = line.split(" ")
        if (content[0] == "$") break

        if (content[0] == "dir") currentElement.add(TreeNode(Pair(content[1], 0)))
        else {
            currentElement.add(TreeNode(Pair(content[1], content[0].toInt())))
        }
        if (scanner.hasNext()) line = scanner.nextLine()
        else line = ""
    }
    return line
}




