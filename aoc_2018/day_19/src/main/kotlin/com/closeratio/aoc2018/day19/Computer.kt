package com.closeratio.aoc2018.day19

import com.closeratio.aoc2018.day19.instructions.*

open class Computer protected constructor(
        val instructions: List<Instruction>,
        var ipRegister: Int
) {

    var instructionPointer: Int = 0
    val registers = arrayOf(0, 0, 0, 0, 0, 0)

    var executionCount = 0
    private val preExecCallbacks = hashMapOf<Int, ArrayList<() -> Boolean>>()

    fun addPreExecCallback(lineNumber: Int, callback: () -> Boolean) {
        preExecCallbacks.getOrPut(lineNumber, { ArrayList() }).add(callback)
    }

    fun executeInstruction() {
        // Write IP value to bound register
        registers[ipRegister] = instructionPointer

        // Execute instruction
        instructions[instructionPointer].process(registers)

        // Write value from bound register back to IP register
        instructionPointer = registers[ipRegister]

        // Increment instruction pointer
        instructionPointer++
    }

    open fun executeUntilFinished() {
        while (instructionPointer >= 0 && instructionPointer < instructions.size) {
            if (instructionPointer in preExecCallbacks &&
                    preExecCallbacks.getValue(instructionPointer)
                            .map { it() }
                            .any { it }) {
                return
            }

            executeInstruction()
            executionCount++
        }
    }

    companion object {
        fun from(inputLines: List<String>): Computer {
            val ipLine = inputLines.first()
            val instructionLines = inputLines.drop(1)
            return Computer(
                    instructionLines.map { parseInstruction(it) },
                    ipLine.split(" ")[1].toInt()
            )
        }

        private fun parseInstruction(line: String): Instruction {
            val elements = line.split(" ")
            val operation = elements.first()
            val params = elements.drop(1).map { it.toInt() }

            val instruction = when (operation) {
                "addr" -> AddRegister(params[0], params[1], params[2])
                "addi" -> AddImmediate(params[0], params[1], params[2])
                "mulr" -> MultiplyRegister(params[0], params[1], params[2])
                "muli" -> MultiplyImmediate(params[0], params[1], params[2])
                "banr" -> BitwiseAndRegister(params[0], params[1], params[2])
                "bani" -> BitwiseAndImmediate(params[0], params[1], params[2])
                "borr" -> BitwiseOrRegister(params[0], params[1], params[2])
                "bori" -> BitwiseOrImmediate(params[0], params[1], params[2])
                "setr" -> SetRegister(params[0], params[1], params[2])
                "seti" -> SetImmediate(params[0], params[1], params[2])
                "gtir" -> GreaterThanImmediateRegister(params[0], params[1], params[2])
                "gtri" -> GreaterThanRegisterImmediate(params[0], params[1], params[2])
                "gtrr" -> GreaterThanRegisterRegister(params[0], params[1], params[2])
                "eqir" -> EqualImmediateRegister(params[0], params[1], params[2])
                "eqri" -> EqualRegisterImmediate(params[0], params[1], params[2])
                "eqrr" -> EqualRegisterRegister(params[0], params[1], params[2])
                else -> throw IllegalArgumentException("Unknown operation \"$operation\" for line \"$line\"")
            }

            // This shouldn't throw unless the instruction has been implemented incorrectly
            check(instruction.name == operation) {
                "Operation name $operation does not match name of instruction ${instruction.name}"
            }

            return instruction
        }
    }

}