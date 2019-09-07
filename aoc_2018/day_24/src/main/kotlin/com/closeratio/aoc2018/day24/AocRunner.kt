package com.closeratio.aoc2018.day24

import com.closeratio.aoc2018.day24.Allegiance.INFECTION

object AocRunner {
    val battlefield = BattlefieldFactory.from(javaClass
            .getResource("/input.txt")
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })

    fun runPart1() {
        val result = battlefield.fightUntilFinished()
        println(result)
    }

    fun runPart2() {
        var boost = 0
        var currBattlefield = battlefield.plusBoost(boost)
        while ((currBattlefield.fightUntilFinished()?.winningAllegiance ?: INFECTION) == INFECTION) {
            ++boost
            currBattlefield = battlefield.plusBoost(boost)
        }

        val result = battlefield.plusBoost(boost).fightUntilFinished()
		println(result)
    }
}

fun main() {
    AocRunner.runPart2()
}