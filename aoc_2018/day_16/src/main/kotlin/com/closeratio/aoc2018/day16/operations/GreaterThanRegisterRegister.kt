package com.closeratio.aoc2018.day16.operations

class GreaterThanRegisterRegister: Operation(4) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return if (registers.getValue(parameterA) > registers.getValue(parameterB)) 1 else 0
	}
}