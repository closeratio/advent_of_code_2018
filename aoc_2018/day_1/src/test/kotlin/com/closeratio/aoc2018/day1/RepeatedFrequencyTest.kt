package com.closeratio.aoc2018.day1

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day1.FrequencyParser.computeRepeatedFrequency
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class RepeatedFrequencyTest {

	@Test
	fun input4() {
		val data = ResourceLoader.loadResource("/test_input_4.txt").data
		assertThat(computeRepeatedFrequency(data), `is`(10))
	}

}