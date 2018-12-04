package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day3.ClaimCalculator.calculateOverlappingClaimCount
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ClaimCalculatorTest {

	@Test
	fun testInput1() {
		val result = calculateOverlappingClaimCount(
				ResourceLoader.loadResource("/test_input_1.txt").data.split("\n")
		)

		assertThat(result, `is`(4))
	}

}