package com.closeratio.aoc2018.day15

class CombatSimulation private constructor(
		val entities: List<Entity>
) {

	companion object {
		fun from(data: String): CombatSimulation {
			return CombatSimulation(listOf())
		}
	}
}