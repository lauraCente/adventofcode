package advent2022.utils

data class Point3D (val x: Int, val y: Int, val z:Int) {
    val cubePoints get() = listOf<Point3D>(Point3D(x, y, z), Point3D(x+1, y, z), Point3D(x, y+1, z), Point3D(x, y, z+1), Point3D(x+1, y+1, z), Point3D(x+1, y, z+1), Point3D(x, y+1, z+1), Point3D(x+1, y+1, z+1))

    var exposedSides = 6

    fun addAdjacent(p:Point3D){
        if (elementsEquals(cubePoints, p.cubePoints, 3)){
            exposedSides--
            p.exposedSides--
        }
    }





}