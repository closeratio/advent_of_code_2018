package com.closeratio.aoc2018.day16.operations

class BitwiseOrRegister: Operation(8) {
	override fun calculateOutputValue(registers: Map<Int, Int>, parameterA: Int, parameterB: Int): Int {
		return registers.getValue(parameterA).or(registers.getValue(parameterB	))
	}
}