
package advent2022.utils
class TreeNode<T>(val data: T) {
    val children = mutableListOf<TreeNode<T>>()
    var parent :TreeNode<T>? = null

    fun add(newchild: TreeNode<T>) {
        newchild.parent = this
        this.children.add(newchild)
    }


    fun forEachDepthFirstElem( action: (T) -> Unit) {
        action(this.data)
        children.forEach {
            it.forEachDepthFirstElem(action)
        }
    }

    fun forEachDepthFirst( action: (TreeNode<T>) -> Unit) {

        children.forEach {
            it.forEachDepthFirst(action)
        }
        action(this)
    }


    fun forEach(function: (TreeNode<T>) -> Unit) {
        function(this)
        children.forEach {
            it.forEach(function)
        }
    }

//    fun sumOf( action: (Int) -> Int) {
//
//        var x = children.forEach {
//            it.sumOf(action)
//        }
//        var sol = x + (Int)this.data
//        return sol
//    }



}


