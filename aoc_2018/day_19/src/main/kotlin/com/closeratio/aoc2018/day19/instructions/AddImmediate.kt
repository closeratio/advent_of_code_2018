package com.closeratio.aoc2018.day19.instructions

class AddImmediate(paramA: Int, paramB: Int, paramC: Int): Instruction("addi", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return registers[parameterA] + parameterB
	}
}