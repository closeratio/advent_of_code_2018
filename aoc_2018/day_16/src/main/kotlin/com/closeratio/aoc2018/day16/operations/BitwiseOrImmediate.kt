package com.closeratio.aoc2018.day16.operations

class BitwiseOrImmediate: Operation(5) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return registers.getValue(parameterA).or(parameterB)
	}
}