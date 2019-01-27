package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val stateChanges = StateChangeParser.parseStateChanges(ResourceLoader.loadResource("/state_changes.txt").data)
		val threeOrMoreCount = stateChanges
				.filter { MatchingOperationsChecker.check(it).size >= 3 }
				.size

		println(threeOrMoreCount)
	}

}