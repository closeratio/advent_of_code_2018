package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day3.InventoryManager.calculateChecksum
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class CalculateChecksumTest {

	@Test
	fun input1() {
		val result = calculateChecksum(loadResource("/test_input_1.txt").data.split("\n"))
		assertThat(result, Matchers.`is`(6))
	}

}