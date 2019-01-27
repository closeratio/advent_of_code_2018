package com.closeratio.aoc2018.day15

object CombatSimulationAttackManipulator {

	fun computeOutcome(data: String): Int {
		var elfAttackPower = 4

		while (true) {
			val sim = CombatSimulation.from(data, elfAttackPower)
			val initialElfCount = sim.entities.filterIsInstance<Elf>().size

			val outcome = sim.computeOutcome()

			if (sim.entities.filterIsInstance<Elf>().size == initialElfCount) {
				return outcome
			}

			elfAttackPower++
		}
	}

}