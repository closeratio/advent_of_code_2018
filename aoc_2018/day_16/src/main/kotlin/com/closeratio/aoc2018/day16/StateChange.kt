package com.closeratio.aoc2018.day16

class StateChange(
		val preState: ProgramState,
		val instruction: Instruction,
		val postState: ProgramState
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as StateChange

		if (preState != other.preState) return false
		if (instruction != other.instruction) return false
		if (postState != other.postState) return false

		return true
	}

	override fun hashCode(): Int {
		var result = preState.hashCode()
		result = 31 * result + instruction.hashCode()
		result = 31 * result + postState.hashCode()
		return result
	}

	override fun toString(): String {
		return "StateChange(preState=$preState, instruction=$instruction, postState=$postState)"
	}

}
