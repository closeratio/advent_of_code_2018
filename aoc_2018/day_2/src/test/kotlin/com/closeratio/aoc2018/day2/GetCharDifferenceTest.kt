package com.closeratio.aoc2018.day2

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class GetCharDifferenceTest {

	@Test
	fun example1() {
		val result = InventoryManager.getCharDifference(
				"abcde",
				"axcye")

		assertThat(result, `is`(2))
	}

	@Test
	fun example2() {
		val result = InventoryManager.getCharDifference(
				"fghij",
				"fguij")

		assertThat(result, `is`(1))
	}

	@Test
	fun example3() {
		val result = InventoryManager.getCharDifference(
				"abcde",
				"abcde")

		assertThat(result, `is`(0))
	}

}