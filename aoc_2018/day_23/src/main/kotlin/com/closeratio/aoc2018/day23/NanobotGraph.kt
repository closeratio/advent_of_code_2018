package com.closeratio.aoc2018.day23

import com.closeratio.aoc2018.common.math.Vec3i

class NanobotGraph private constructor(
        val nanobots: Set<Nanobot>
) {

    init {
        nanobots.forEach { it.updateSharedPointNeighbours(nanobots) }
    }

    fun getNanobotWithLargestRadius(): Nanobot = nanobots.maxBy { it.signalRadius }!!

    fun getNanobotsInRangeOfNanobot(nanobot: Nanobot): Set<Nanobot> = nanobots
            .filter { it.position.manhattan(nanobot.position) <= nanobot.signalRadius }
            .toSet()

    fun getDistanceToOptimumPosition(): Int {
        return BronKerbosch
                .largestClique(nanobots)
                .map { it.position.manhattan(Vec3i.ZERO) - it.signalRadius }
                .max()!!
    }

    companion object {
        private val lineRegex = Regex("<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(\\d+)")

        fun from(data: List<String>): NanobotGraph {
            return NanobotGraph(data
                    .map {
                        val groups = lineRegex.find(it)!!
                                .groupValues

                        Nanobot(
                                Vec3i.from(groups[1].toInt(), groups[2].toInt(), groups[3].toInt()),
                                groups[4].toInt()
                        )
                    }
                    .toSet())
        }
    }

}
