package com.closeratio.aoc2018.day12

class StateTransition(
		val newState: List<Boolean>,
		val indexOffset: Int
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as StateTransition

		if (newState != other.newState) return false
		if (indexOffset != other.indexOffset) return false

		return true
	}

	override fun hashCode(): Int {
		var result = newState.hashCode()
		result = 31 * result + indexOffset
		return result
	}

	override fun toString(): String {
		return "StateTransition(newState=$newState, indexOffset=$indexOffset)"
	}

}