package com.closeratio.aoc2018.day25

import com.closeratio.aoc2018.common.math.Vec4i

class Constellation(
        val points: MutableSet<Vec4i>
) {
    fun isWithinConstellation(vec: Vec4i): Boolean = points.any { it.manhattan(vec) <= 3 }

    fun addPoint(vec: Vec4i) {
        points.add(vec)
    }

    operator fun plus(other: Constellation): Constellation {
        return Constellation((points + other.points).toMutableSet())
    }
}