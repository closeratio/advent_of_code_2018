package com.closeratio.aoc2018.day18

class LumberAreaSimulation(
		val initialState: LumberAreaState
) {

	fun iterate(count: Int): LumberAreaState {
		if (count <= 0) {
			return initialState
		}

		var currState = initialState

		(1..count).forEach {
			currState = currState.next()
		}

		return currState
	}

}