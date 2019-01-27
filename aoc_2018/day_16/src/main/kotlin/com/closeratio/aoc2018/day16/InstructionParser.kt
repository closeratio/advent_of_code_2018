package com.closeratio.aoc2018.day16

object InstructionParser {

	val INSTRUCTION_REGEX = """(\d+) (\d+) (\d+) (\d+)""".toRegex()

	fun parse(line: String): Instruction {
		val result = INSTRUCTION_REGEX.find(line) ?: throw IllegalArgumentException("Malformed instruction: $line")

		return Instruction(
				result.groupValues[1].toInt(),
				OperationParameters(
						result.groupValues[2].toInt(),
						result.groupValues[3].toInt(),
						result.groupValues[4].toInt()))
	}

}