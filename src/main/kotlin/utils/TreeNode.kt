
package advent2022.utils
class TreeNode<T>(val data: T) {
    val children = mutableListOf<TreeNode<T>>()
    var parent :TreeNode<T>? = null

    fun add(newchild: TreeNode<T>) {
        newchild.parent = this
        this.children.add(newchild)
    }

    fun forEachDepthFirst( action: (TreeNode<T>) -> Unit) {
        children.forEach {
            it.forEachDepthFirst(action)
        }
        action(this)
    }

    fun <Z>toListOfDepthFirst( action: (TreeNode<T>) -> Z):List<Z> {
        val result = mutableListOf<Z>()
        children.forEach {
            result.addAll(it.toListOfDepthFirst(action))
        }
        result+=action(this)
        return result
    }

}


