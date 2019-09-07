package com.closeratio.aoc2018.day25

object AocRunner {

    fun runPart1() {
        val constellationsSet = ConstellationFactory.fromLines(javaClass
                .getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() })

        println(constellationsSet.size)
    }

}

fun main() {
    AocRunner.runPart1()
}