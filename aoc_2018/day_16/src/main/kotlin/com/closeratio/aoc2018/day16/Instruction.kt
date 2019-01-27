package com.closeratio.aoc2018.day16

class Instruction(
		val opcode: Int,
		val parameters: OperationParameters
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Instruction

		if (opcode != other.opcode) return false
		if (parameters != other.parameters) return false

		return true
	}

	override fun hashCode(): Int {
		var result = opcode
		result = 31 * result + parameters.hashCode()
		return result
	}

	override fun toString(): String {
		return "Instruction(opcode=$opcode, parameters=$parameters)"
	}

}
