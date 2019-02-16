package com.closeratio.aoc2018.day18

class LumberAreaSimulation(
		val initialState: LumberAreaState
) {

	val stateMap = LinkedHashMap<LumberAreaState, LumberAreaState>()

	fun iterate(count: Int): LumberAreaState {
		if (count <= 0) {
			return initialState
		}

		var currState = initialState

		(1..count).forEach { currInterationIndex ->
			if (currState in stateMap) {
				val sequenceStart = stateMapKeyIndex(currState)
				val sequenceEnd = currInterationIndex - 1

				val sequenceSize = sequenceEnd - sequenceStart
				val remainingIters = (count - sequenceStart) % sequenceSize
				repeat(remainingIters) {
					currState = stateMap[currState]!!
				}

				return currState
			} else {
				val nextState = currState.next()
				stateMap[currState] = nextState
				currState = nextState
			}
		}

		return currState
	}

	private fun stateMapKeyIndex(state: LumberAreaState): Int {
		if (state !in stateMap) {
			throw IllegalArgumentException("State not in map: $state")
		}

		stateMap.entries.forEachIndexed { index, entry ->
			if (entry.key == state) {
				return index
			}
		}

		throw IllegalStateException("Should not have reached this point")
	}

}