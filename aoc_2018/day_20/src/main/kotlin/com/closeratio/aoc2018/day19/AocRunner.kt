package com.closeratio.aoc2018.day19

object AocRunner {

    val graph = GraphParser.parseGraph(javaClass
            .getResource("/input.txt")
            .readText())

    fun runPart1() {
        val result = graph.calculateFurthestNode()
        println(result)
    }

    fun runPart2() {
        val result = graph.calculateDistanceMap()
                .filter { it.value >= 1000 }
                .count()

        println(result)
    }

}

fun main() {
    AocRunner.runPart2()
}