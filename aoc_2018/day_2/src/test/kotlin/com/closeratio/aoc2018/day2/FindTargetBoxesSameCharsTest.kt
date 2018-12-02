package com.closeratio.aoc2018.day2

import com.closeratio.aoc2018.day2.InventoryManager.findTargetBoxesSameChars
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FindTargetBoxesSameCharsTest {

	@Test
	fun example1() {
		val result = findTargetBoxesSameChars(listOf(
				"abcde",
				"fghij",
				"klmno",
				"pqrst",
				"fguij",
				"axcye",
				"wvxyz"))

		assertThat(result, `is`("fgij"))
	}


}