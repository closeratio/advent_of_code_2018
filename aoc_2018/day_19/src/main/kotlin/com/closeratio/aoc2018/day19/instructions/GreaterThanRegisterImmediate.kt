package com.closeratio.aoc2018.day19.instructions

class GreaterThanRegisterImmediate(paramA: Int, paramB: Int, paramC: Int): Instruction("gtri", paramA, paramB, paramC) {
	override fun calculateOutputValue(registers: Array<Int>): Int {
		return if (registers[parameterA] > parameterB) 1 else 0
	}
}