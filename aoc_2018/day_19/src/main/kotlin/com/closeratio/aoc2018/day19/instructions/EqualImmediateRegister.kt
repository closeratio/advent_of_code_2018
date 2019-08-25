package com.closeratio.aoc2018.day19.instructions

class EqualImmediateRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("eqir", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return if (parameterA == registers[parameterB]) 1 else 0
	}
}