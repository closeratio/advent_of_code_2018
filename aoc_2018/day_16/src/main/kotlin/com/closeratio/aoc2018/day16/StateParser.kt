package com.closeratio.aoc2018.day16

object StateParser {

	val STATE_REGEX = """\w+:\s+\[(\d+), (\d+), (\d+), (\d+)]""".toRegex()

	fun parse(line: String): ProgramState {
		val result = STATE_REGEX.find(line) ?: throw IllegalArgumentException("Malformed state: $line")

		return ProgramState(mapOf(
				0 to result.groupValues[1].toInt(),
				1 to result.groupValues[2].toInt(),
				2 to result.groupValues[3].toInt(),
				3 to result.groupValues[4].toInt()
		))
	}

}