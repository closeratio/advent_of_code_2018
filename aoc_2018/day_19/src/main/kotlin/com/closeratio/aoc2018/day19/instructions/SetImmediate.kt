package com.closeratio.aoc2018.day19.instructions

class SetImmediate(paramA: Int, paramB: Int, paramC: Int) : Instruction("seti", paramA, paramB, paramC) {
    override fun calculateOutputValue(registers: Array<Int>): Int {
        return parameterA
    }
}