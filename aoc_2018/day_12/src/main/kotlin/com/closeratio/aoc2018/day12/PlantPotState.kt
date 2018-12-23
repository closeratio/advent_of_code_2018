package com.closeratio.aoc2018.day12

class PlantPotState private constructor(
		val baseIndex: Long,
		val states: List<Boolean>
) {

	fun produceNewState(ruleset: Ruleset, transitionCache: TransitionCache): PlantPotState {
		val pots = ArrayList(states)

		// Check cache
		if (states in transitionCache.cache) {
			val cached = transitionCache.cache[states]!!
			return PlantPotState(
					baseIndex + cached.indexOffset,
					cached.newState)
		}

		// Pad start and end
		pots.addAll(0, listOf(false, false))
		pots.addAll(listOf(false, false))

		// Apply ruleset to each one
		val newStates = pots.mapIndexed { index, state ->
			val farLeftIndex = index - 2
			val leftIndex = index - 1
			val rightIndex = index + 1
			val farRightIndex = index + 2

			val input = RuleInput(
					if (farLeftIndex >= 0) pots[farLeftIndex] else false,
					if (leftIndex >= 0) pots[leftIndex] else false,
					state,
					if (rightIndex < pots.size) pots[rightIndex] else false,
					if (farRightIndex < pots.size) pots[farRightIndex] else false)

			ruleset.rules[input]?.outputState ?: false
		}

		// Calculate offset
		val offset = newStates.indexOf(true) - 2

		val trimmedStates = newStates.subList(
				newStates.indexOf(true),
				newStates.lastIndexOf(true) + 1)

		// Store transition in cache
		transitionCache.cache[states] = StateTransition(
				trimmedStates,
				offset)

		return PlantPotState(
				baseIndex + offset,
				trimmedStates)
	}

	companion object {
		private val STATE_REGEX = """initial state: ([#.]+)""".toRegex()

		fun from(data: List<String>): PlantPotState {
			val stateString = STATE_REGEX.find(data[0])!!.groupValues[1]
			return PlantPotState(0, stateString.map { it == '#' })
		}

		fun from(baseIndex: Long, states: List<Boolean>): PlantPotState {
			return PlantPotState(baseIndex, states)
		}
	}

	override fun toString(): String {
		val sb = StringBuilder("$baseIndex: ")

		states.map { if (it) "#" else "." }
				.forEach { sb.append(it) }

		return sb.toString()
	}

}

