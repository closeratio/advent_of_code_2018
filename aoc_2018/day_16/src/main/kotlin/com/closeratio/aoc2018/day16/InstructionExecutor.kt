package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.day16.operations.OperationType
import java.util.*

object InstructionExecutor {

	val opcodeMap = OperationType.values()
			.map { it.operation }
			.associateBy { it.opcode }

	fun executeInstructions(instructions: List<Instruction>): ProgramState {
		val instructionQueue = LinkedList(instructions)
		val programStates = arrayListOf(ProgramState(mapOf(
				0 to 0, 1 to 0, 2 to 0, 3 to 0
		)))

		while (instructionQueue.isNotEmpty()) {
			val instruction = instructionQueue.pop()
			val operation = opcodeMap[instruction.opcode]!!
			val currentState = programStates.last()
			
			programStates.add(operation.process(currentState, instruction.parameters))
		}

		return programStates.last()
	}

}