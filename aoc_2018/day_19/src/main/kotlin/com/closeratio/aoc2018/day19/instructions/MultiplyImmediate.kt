package com.closeratio.aoc2018.day19.instructions

class MultiplyImmediate(paramA: Int, paramB: Int, paramC: Int): Instruction("muli", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA] * parameterB
	}
}