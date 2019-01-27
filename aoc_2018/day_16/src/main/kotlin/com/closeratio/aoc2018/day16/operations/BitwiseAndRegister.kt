package com.closeratio.aoc2018.day16.operations

class BitwiseAndRegister: Operation(-1) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return registers.getValue(parameterA).and(registers.getValue(parameterB))
	}
}