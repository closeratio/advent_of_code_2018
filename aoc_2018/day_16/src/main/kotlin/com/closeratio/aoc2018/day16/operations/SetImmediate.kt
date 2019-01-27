package com.closeratio.aoc2018.day16.operations

class SetImmediate: Operation(7) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return parameterA
	}
}