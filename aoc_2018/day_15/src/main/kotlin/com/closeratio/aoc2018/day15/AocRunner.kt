package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main (args: Array<String>) {
		val data = ResourceLoader.loadResource("/input.txt").data

		val sim = CombatSimulation.from(data)
		val part1Outcome = sim.computeOutcome()
		println(part1Outcome)

		val part2Outcome = CombatSimulationAttackManipulator.computeOutcome(data)
		println(part2Outcome)
	}

}