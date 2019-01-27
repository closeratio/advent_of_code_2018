package com.closeratio.aoc2018.day16

class ProgramState(
		val registerStates: Map<Int, Int>
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as ProgramState

		if (registerStates != other.registerStates) return false

		return true
	}

	override fun hashCode(): Int {
		return registerStates.hashCode()
	}

	override fun toString(): String {
		return "ProgramState(registerStates=$registerStates)"
	}

}