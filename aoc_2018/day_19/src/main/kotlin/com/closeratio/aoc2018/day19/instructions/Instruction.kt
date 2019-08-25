package com.closeratio.aoc2018.day19.instructions

abstract class Instruction(
        val name: String,
        val parameterA: Int,
        val parameterB: Int,
        val parameterC: Int
) {

    fun process(registers: Array<Int>) {
        registers[parameterC] = calculateOutputValue(registers)
    }

    abstract fun calculateOutputValue(registers: Array<Int>): Int

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Instruction

        if (parameterA != other.parameterA) return false
        if (parameterB != other.parameterB) return false
        if (parameterC != other.parameterC) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = parameterA
        result = 31 * result + parameterB
        result = 31 * result + parameterC
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Instruction($name $parameterA $parameterB $parameterC)"
    }

}

