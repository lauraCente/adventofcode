package advent2022.utils

fun <T> List<List<T>>.printMatrix() {
    for(i in this.indices ){
        for (j in this[i].indices){
            print("${this[i][j]} ")
        }
        println()
    }
}