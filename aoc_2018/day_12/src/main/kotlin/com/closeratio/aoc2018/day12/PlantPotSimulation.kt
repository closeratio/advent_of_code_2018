package com.closeratio.aoc2018.day12

class PlantPotSimulation private constructor(
		initialState: PlantPotState,
		val ruleset: Ruleset
) {

	var state = initialState

	fun iterate(iterCount: Long) {
		LongRange(1, iterCount).forEach {
			state = state.produceNewState(ruleset)
		}
	}

	fun sumOfPlantIndices(): Int {
		return state.plantPots
				.map { if (it.value) it.key else 0 }
				.sum()
	}

	companion object {
		fun from(data: List<String>): PlantPotSimulation {
			return PlantPotSimulation(
					PlantPotState.from(data),
					Ruleset.from(data))
		}
	}
}