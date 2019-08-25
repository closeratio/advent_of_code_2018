package com.closeratio.aoc2018.day19.instructions

class EqualRegisterImmediate(paramA: Int, paramB: Int, paramC: Int): Instruction("eqri", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return if (registers[parameterA] == parameterB) 1 else 0
	}
}