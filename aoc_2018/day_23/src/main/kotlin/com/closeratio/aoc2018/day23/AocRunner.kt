package com.closeratio.aoc2018.day23

object AocRunner {

    val graph = NanobotGraph.from(javaClass
            .getResource("/input.txt")
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })

    fun runPart1() {
        val inRange = graph.getNanobotsInRangeOfNanobot(graph.getNanobotWithLargestRadius())

        println(inRange.size)
    }

    fun runPart2() {
        val inRange = graph.getDistanceToOptimumPosition()

        println(inRange)
    }

}

fun main() {
    AocRunner.runPart2()
}