package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main (args: Array<String>) {
		val sim = CombatSimulation.from(ResourceLoader.loadResource("/input.txt").data)

		val outcome = sim.computeOutcome()

		println(sim.serialise())
		println(outcome)
	}

}