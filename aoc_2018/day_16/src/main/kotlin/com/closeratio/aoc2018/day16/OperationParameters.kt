package com.closeratio.aoc2018.day16

class OperationParameters(
		val input1: Int,
		val input2: Int,
		val outputRegister: Int
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as OperationParameters

		if (input1 != other.input1) return false
		if (input2 != other.input2) return false
		if (outputRegister != other.outputRegister) return false

		return true
	}

	override fun hashCode(): Int {
		var result = input1
		result = 31 * result + input2
		result = 31 * result + outputRegister
		return result
	}

	override fun toString(): String {
		return "OperationParameters(input1=$input1, input2=$input2, outputRegister=$outputRegister)"
	}

}
