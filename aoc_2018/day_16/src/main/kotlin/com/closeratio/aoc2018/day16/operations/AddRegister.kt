package com.closeratio.aoc2018.day16.operations

class AddRegister: Operation(1) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return registers.getValue(parameterA) + registers.getValue(parameterB)
	}
}