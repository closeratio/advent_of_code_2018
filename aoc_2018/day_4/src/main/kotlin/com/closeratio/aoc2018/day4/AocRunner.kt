package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day4.OptimalGuardCalculator.computeOptimalGuardStrategy

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result = computeOptimalGuardStrategy(loadResource("/input.txt").data.split("\n"))

		println(result.first.id * result.second)
	}
}