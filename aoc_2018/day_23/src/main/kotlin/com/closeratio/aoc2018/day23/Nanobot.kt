package com.closeratio.aoc2018.day23

import com.closeratio.aoc2018.common.math.Vec3i

data class Nanobot(
        val position: Vec3i,
        val signalRadius: Int
) {

    val neighbours = hashSetOf<Nanobot>()

    private fun isInRangeOfSharedPoint(nanobot: Nanobot): Boolean = position.manhattan(nanobot.position) <= signalRadius + nanobot.signalRadius

    fun updateSharedPointNeighbours(nanobots: Set<Nanobot>) {
        neighbours.clear()
        neighbours.addAll(nanobots
                .filter { it != this }
                .filter { isInRangeOfSharedPoint(it) })
    }


}