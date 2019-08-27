package com.closeratio.aoc2018.day23

import com.closeratio.aoc2018.common.math.Vec3i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestNanobotGraph {

    val graph1 = NanobotGraph.from(javaClass
            .getResource("/test_input_1.txt")
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })

    val graph2 = NanobotGraph.from(javaClass
            .getResource("/test_input_2.txt")
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })

    @Test
    fun from() {
        assertThat(graph1.nanobots.size, `is`(9))

        assertThat(graph1.nanobots.contains(Nanobot(Vec3i.ZERO, 4)), `is`(true))
        assertThat(graph1.nanobots.contains(Nanobot(Vec3i.from(0, 5, 0), 3)), `is`(true))
        assertThat(graph1.nanobots.contains(Nanobot(Vec3i.from(1, 1, 2), 1)), `is`(true))
    }

    @Test
    fun nanobotWithLargestRadius() {
        val nanobot = graph1.getNanobotWithLargestRadius()
        assertThat(nanobot.position, `is`(Vec3i.ZERO))
        assertThat(nanobot.signalRadius, `is`(4))
    }

    @Test
    fun nanobotsInRangeOfNanobot() {
        val inRangeSet = graph1.getNanobotsInRangeOfNanobot(graph1.getNanobotWithLargestRadius())

        assertThat(inRangeSet.size, `is`(7))
        assertThat(inRangeSet.contains(Nanobot(Vec3i.ZERO, 4)), `is`(true))
        assertThat(inRangeSet.contains(Nanobot(Vec3i.from(4, 0, 0), 3)), `is`(true))
        assertThat(inRangeSet.contains(Nanobot(Vec3i.from(1, 1, 2), 1)), `is`(true))

        assertThat(inRangeSet.contains(Nanobot(Vec3i.from(0, 5, 0), 3)), `is`(false))
    }

    @Test
    fun distanceToOptimumPosition() {
        val distance = graph2.getDistanceToOptimumPosition()

        assertThat(distance, `is`(36))
    }

}