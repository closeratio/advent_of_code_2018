package com.closeratio.aoc2018.day23

import com.closeratio.aoc2018.common.math.Vec3i

data class Nanobot(
        val position: Vec3i,
        val signalRadius: Int
) {

    fun isPosInRange(pos: Vec3i): Boolean = isPosInRange(pos.x, pos.y, pos.z)
    fun isPosInRange(x: Int, y: Int, z: Int): Boolean = position.manhattan(x, y, z) <= signalRadius

}