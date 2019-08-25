package com.closeratio.aoc2018.day19.instructions

class AddRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("addr", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA] + registers[parameterB]
	}
}