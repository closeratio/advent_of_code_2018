package com.closeratio.aoc2018.day16

import java.util.*

object StateChangeParser {

	fun parseStateChanges(data: String): List<StateChange> {
		val lines = LinkedList(data
				.trim()
				.split("\n")
				.map { it.trim() }
				.filter { it.isNotEmpty() })


		if (lines.size % 3 != 0) {
			throw IllegalArgumentException("Input does not have valid number of lines")
		}

		val stateChanges = arrayListOf<StateChange>()

		while (lines.isNotEmpty()) {
			val preState = StateParser.parse(lines.pop())
			val instruction = InstructionParser.parse(lines.pop())
			val postState = StateParser.parse(lines.pop())

			stateChanges.add(StateChange(preState, instruction, postState))
		}

		return stateChanges
	}

}