package com.closeratio.aoc2018.day16.operations

import com.closeratio.aoc2018.day16.OperationParameters
import com.closeratio.aoc2018.day16.ProgramState

abstract class Operation(
		val opcode: Int
) {

	fun process(state: ProgramState, parameters: OperationParameters): ProgramState {
		val map = HashMap(state.registerStates)
		map[parameters.outputRegister] = calculateOutputValue(state.registerStates, parameters.input1, parameters.input2)
		return ProgramState(map)
	}

	abstract fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Operation

		if (opcode != other.opcode) return false

		return true
	}

	override fun hashCode(): Int {
		return opcode
	}

}

