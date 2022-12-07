package advent2022

import advent2022.utils.TreeNode
import com.sun.source.tree.BinaryTree
import java.util.*
import kotlin.io.path.Path

fun main() {
    val scanner = Scanner(Path("src/main/resources/2022day7_1.txt"))

    var root = TreeNode<Pair<String, Int>>(Pair("/", 0))
    var currentElement = root
    var pair = Pair(currentElement,"")
    var text = scanner.nextLine()
    while (text != "") {
        var line = text.split(" ")
        if (line[0] == "\$") {
            pair = executeCommand(line, currentElement, scanner)
            text = pair.second
            currentElement = pair.first
        }
    }
    root.forEachDepthFirst {
        var sum = 0
        if (it.data.second == 0){
            sum = sumInDepth(it)
            if (sum>2476859)  println("${it.data.first} ${sum}")
        }

    }

//    root.forEach {
//        var x = sumInDepth(it)
//        println(x)
//    }

}

fun sumInDepth(currentElement: TreeNode<Pair<String, Int>>): Int {
   var size: Int
    if (currentElement.children.size==0) size = 0
    else {
        var currentSize = currentElement.data.second
        var s = 0
        for (element in currentElement.children){
            s += sumInDepth(element) + element.data.second
        }
        size = currentSize+s
    }
    return size
}

fun executeCommand(command: List<String>, currentElement: TreeNode<Pair<String, Int>>, scanner: Scanner): Pair<TreeNode<Pair<String,Int>>, String> {
    var pair = Pair(currentElement,"")
    if (command[1] == "ls")  pair = Pair(currentElement,executeCreateDirectories(currentElement, scanner))
    if (command[1] == "cd") {
        pair = moveIntoDirectory(currentElement,command[2], scanner)
    }
    return pair
}

fun moveIntoDirectory(
    currentElement: TreeNode<Pair<String, Int>>, directoryToMove: String, scanner: Scanner
): Pair<TreeNode<Pair<String,Int>>, String> {
    var elementToMove = currentElement
    if (directoryToMove == "..") { elementToMove = currentElement.parent!!
    }
    for (child in currentElement.children){
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
        var content = line.split(" ")
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


