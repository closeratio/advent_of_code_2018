package com.closeratio.aoc2018.day18

class LumberAreaSimulation(
		val initialState: LumberAreaState
) {

	val stateMap = HashMap<LumberAreaState, LumberAreaState>()

	fun iterate(count: Int): LumberAreaState {
		if (count <= 0) {
			return initialState
		}

		var currState = initialState

		(1..count).forEach {
			if (currState in stateMap) {
				val sequenceSize = it
				val remainingIters = count % sequenceSize
				(1..(remainingIters + 1)).forEach {
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

}