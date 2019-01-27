package com.closeratio.aoc2018.day16.operations

class GreaterThanRegisterImmediate: Operation(12) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return if (registers.getValue(parameterA) > parameterB) 1 else 0
	}
}