package com.closeratio.aoc2018.day19

import com.closeratio.aoc2018.day19.instructions.AddImmediate
import com.closeratio.aoc2018.day19.instructions.AddRegister
import com.closeratio.aoc2018.day19.instructions.SetImmediate
import com.closeratio.aoc2018.day19.instructions.SetRegister
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.Test

class TestComputer {

    val computer = Computer.from(javaClass.getResource("/test_input.txt")
            .readText()
            .split("\n")
            .map { it.trim() }
            .filter { it.isNotBlank() })

    @Test
    fun from() {
        assertThat(computer.ipRegister, `is`(0))
        assertThat(computer.instructions.size, `is`(7))

        assertThat(computer.instructions[0], instanceOf(SetImmediate::class.java))
        assertThat(computer.instructions[1], instanceOf(SetImmediate::class.java))
        assertThat(computer.instructions[2], instanceOf(AddImmediate::class.java))
        assertThat(computer.instructions[3], instanceOf(AddRegister::class.java))
        assertThat(computer.instructions[4], instanceOf(SetRegister::class.java))
        assertThat(computer.instructions[5], instanceOf(SetImmediate::class.java))
        assertThat(computer.instructions[6], instanceOf(SetImmediate::class.java))
    }

    @Test
    fun executeLine() {

        computer.executeInstruction()
        assertThat(computer.instructionPointer, `is`(1))
        assertThat(computer.registers, `is`(arrayOf(0, 5, 0, 0, 0, 0)))

        computer.executeInstruction()
        assertThat(computer.instructionPointer, `is`(2))
        assertThat(computer.registers, `is`(arrayOf(1, 5, 6, 0, 0, 0)))

        computer.executeInstruction()
        assertThat(computer.instructionPointer, `is`(4))
        assertThat(computer.registers, `is`(arrayOf(3, 5, 6, 0, 0, 0)))

        computer.executeInstruction()
        assertThat(computer.instructionPointer, `is`(6))
        assertThat(computer.registers, `is`(arrayOf(5, 5, 6, 0, 0, 0)))

        computer.executeInstruction()
        assertThat(computer.instructionPointer, `is`(7))
        assertThat(computer.registers, `is`(arrayOf(6, 5, 6, 0, 0, 9)))
    }

    @Test
    fun executeUntilFinished() {
        computer.executeUntilFinished()
        assertThat(computer.instructionPointer, `is`(7))
        assertThat(computer.registers, `is`(arrayOf(6, 5, 6, 0, 0, 9)))
    }

}