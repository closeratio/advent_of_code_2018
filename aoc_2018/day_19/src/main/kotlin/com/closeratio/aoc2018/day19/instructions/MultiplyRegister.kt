package com.closeratio.aoc2018.day19.instructions

class MultiplyRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("mulr", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA] * registers[parameterB]
	}
}

