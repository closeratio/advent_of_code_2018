package com.closeratio.aoc2018.day19.instructions

class BitwiseAndImmediate(paramA: Int, paramB: Int, paramC: Int): Instruction("bani", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA].and(parameterB)
	}
}