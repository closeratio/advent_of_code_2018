package com.closeratio.aoc2018.day12

class PlantPotState private constructor(
		val plantPots: Map<Int, Boolean>
) {

	fun produceNewState(ruleset: Ruleset): PlantPotState {
		val pots = HashMap(plantPots)

		// Expand if necessary
		plantPots.forEach { index, state ->
			if (state) {
				IntRange(index - 2, index + 2).forEach {
					if (it !in pots) {
						pots[it] = false
					}
				}
			}
		}

		// Create new state
		return PlantPotState(pots.map { entry ->
					val input = RuleInput(
							pots[entry.key - 2] ?: false,
							pots[entry.key - 1] ?: false,
							entry.value,
							pots[entry.key + 1] ?: false,
							pots[entry.key + 2] ?: false)

					Pair(entry.key, ruleset.rules[input]?.outputState ?: false)
				}.associate { it })
	}

	companion object {
		private val STATE_REGEX = """initial state: ([#.]+)""".toRegex()

		fun from(data: List<String>): PlantPotState {
			val stateString = STATE_REGEX.find(data[0])!!.groupValues[1]
			return PlantPotState(stateString.mapIndexed { index, c ->
				Pair(index, c == '#')
			}.associate { it })
		}
	}

	override fun toString(): String {
		val sb = StringBuilder()

		plantPots.entries
				.sortedBy { it.key }
				.forEach { sb.append(if (it.value) "#" else ".") }

		return sb.toString()
	}

}

