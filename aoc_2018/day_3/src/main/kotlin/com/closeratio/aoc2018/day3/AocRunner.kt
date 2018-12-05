package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day3.ClaimCalculator.calculateOverlappingClaimCount
import com.closeratio.aoc2018.day3.ClaimCalculator.calculateSingleClaimSquareID

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result1 = calculateOverlappingClaimCount(ResourceLoader.loadResource("/input.txt").data.split("\n"))
		println(result1)

		val result2 = calculateSingleClaimSquareID(ResourceLoader.loadResource("/input.txt").data.split("\n"))
		println(result2)
	}

}