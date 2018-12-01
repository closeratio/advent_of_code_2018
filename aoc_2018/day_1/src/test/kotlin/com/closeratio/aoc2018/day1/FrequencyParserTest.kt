package com.closeratio.aoc2018.day1

import com.closeratio.aoc2018.common.ResourceLoader.loadResource
import com.closeratio.aoc2018.day1.FrequencyParser.parseFrequencies
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class FrequencyParserTest {

	@Test
	fun input1() {
		val data = loadResource("/test_input_1.txt").data
		assertThat(parseFrequencies(data), Matchers.`is`(3))
	}

	@Test
	fun input2() {
		val data = loadResource("/test_input_2.txt").data
		assertThat(parseFrequencies(data), Matchers.`is`(0))
	}

	@Test
	fun input3() {
		val data = loadResource("/test_input_3.txt").data
		assertThat(parseFrequencies(data), Matchers.`is`(-6))
	}

}