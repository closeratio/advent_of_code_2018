package com.closeratio.aoc2018.day19.instructions

class BitwiseOrRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("borr", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA].or(registers[parameterB	])
	}
}