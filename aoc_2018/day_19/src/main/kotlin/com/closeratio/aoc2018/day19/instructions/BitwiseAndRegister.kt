package com.closeratio.aoc2018.day19.instructions

class BitwiseAndRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("banr", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA].and(registers[parameterB])
	}
}