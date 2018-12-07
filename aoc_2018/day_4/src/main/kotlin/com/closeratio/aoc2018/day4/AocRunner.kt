package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day4.OptimalGuardCalculator.computeOptimalGuardStrategyAdvanced
import com.closeratio.aoc2018.day4.OptimalGuardCalculator.computeOptimalGuardStrategyBasic

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result1 = computeOptimalGuardStrategyBasic(loadResource("/input.txt").data.split("\n"))
		println(result1.first.id * result1.second)

		val result2 = computeOptimalGuardStrategyAdvanced(loadResource("/input.txt").data.split("\n"))
		println(result2.first.id * result2.second)
	}
}