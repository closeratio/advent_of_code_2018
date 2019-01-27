package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day16.InstructionExecutor.executeInstructions
import com.closeratio.aoc2018.day16.InstructionParser.parse
import com.closeratio.aoc2018.day16.StateChangeParser.parseStateChanges

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val stateChanges = parseStateChanges(loadResource("/state_changes.txt").data)
		val threeOrMoreCount = stateChanges
				.filter { MatchingOperationsChecker.check(it).size >= 3 }
				.size

		println(threeOrMoreCount)

		val finalState = executeInstructions(parse(loadResource("/program_input.txt").data.split("\n")))

		println(finalState)
	}

}