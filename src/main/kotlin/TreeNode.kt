class TreeNode(val data:String ) {
    var child = mutableListOf<TreeNode>()

    fun add(newchild: TreeNode) = this.child.add(newchild)
//    fun forEachDepthFirst(visit: Visitor<T>) {
//        visit(this)
//        children.forEach {
//            it.forEachDepthFirst(visit)
//        }
//    }
}