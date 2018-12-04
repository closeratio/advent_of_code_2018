package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day3.ClaimCalculator.calculateOverlappingClaimCount

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result = calculateOverlappingClaimCount(
				ResourceLoader.loadResource("/input.txt").data.split("\n"))

		println(result)
	}

}