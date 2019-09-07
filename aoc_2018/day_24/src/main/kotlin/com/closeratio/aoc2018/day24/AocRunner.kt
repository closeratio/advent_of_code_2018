package com.closeratio.aoc2018.day24

object AocRunner {
	val battlefield = BattlefieldFactory.from(javaClass
			.getResource("/input.txt")
			.readText()
			.trim()
			.split("\n")
			.map { it.trim() })

	fun runPart1() {
		val unitCount = battlefield.fightUntilFinished()
		println(unitCount)
	}
}

fun main() {
	AocRunner.runPart1()
}