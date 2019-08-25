package com.closeratio.aoc2018.day19.instructions

class EqualRegisterRegister(paramA: Int, paramB: Int, paramC: Int): Instruction("eqrr", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return if (registers[parameterA] == registers[parameterB]) 1 else 0
	}
}