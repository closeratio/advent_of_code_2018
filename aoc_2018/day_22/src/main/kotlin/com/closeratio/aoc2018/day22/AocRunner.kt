package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i

object AocRunner {

    val regionMap = RegionMap(
            5355,
            Vec2i.from(14, 796)
    )

    fun runPart1() {
        println(regionMap.totalRiskLevel())
    }

    fun runPart2() {
        println(regionMap.timeToTarget())
    }

}

fun main() {
    AocRunner.runPart2()
}