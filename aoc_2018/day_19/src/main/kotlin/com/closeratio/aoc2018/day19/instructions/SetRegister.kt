package com.closeratio.aoc2018.day19.instructions

class SetRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("setr", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA]
	}
}