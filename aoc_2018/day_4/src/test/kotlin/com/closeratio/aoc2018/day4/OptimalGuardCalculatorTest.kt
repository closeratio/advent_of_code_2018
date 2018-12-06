package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day4.OptimalGuardCalculator.computeOptimalGuardStrategy
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class OptimalGuardCalculatorTest {

	@Test
	fun input1Test() {
		val optimalGuardPair = computeOptimalGuardStrategy(loadResource("/test_input_1.txt").data.split("\n"))

		assertThat(optimalGuardPair.first.id, `is`(10))
		assertThat(optimalGuardPair.second, `is`(24))
	}

}