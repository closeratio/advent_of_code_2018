package com.closeratio.aoc2018.day16.operations

class EqualImmediateRegister: Operation(10) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return if (parameterA == registers.getValue(parameterB)) 1 else 0
	}
}