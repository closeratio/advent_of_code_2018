package com.closeratio.aoc2018.day23

import com.closeratio.aoc2018.common.math.Vec3i

class NanobotGraph private constructor(
        val nanobots: Set<Nanobot>
) {

    fun getNanobotWithLargestRadius(): Nanobot = nanobots.maxBy { it.signalRadius }!!

    fun getNanobotsInRangeOfNanobot(nanobot: Nanobot): Set<Nanobot> = nanobots
            .filter { it.position.manhattan(nanobot.position) <= nanobot.signalRadius }
            .toSet()

    private fun getNanobotsThatCanReachNanobot(nanobot: Nanobot): Set<Nanobot> = nanobots
            .filter { it.isPosInRange(nanobot.position) }
            .toSet()

    fun getDistanceToOptimumPosition(): Int {
        return 0
    }

    companion object {
        val lineRegex = Regex("<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(\\d+)")

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
