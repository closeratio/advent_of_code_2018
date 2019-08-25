package com.closeratio.aoc2018.day19.instructions

class BitwiseOrImmediate(paramA: Int, paramB: Int, paramC: Int): Instruction("bori", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA].or(parameterB)
	}
}