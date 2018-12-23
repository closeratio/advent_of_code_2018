package com.closeratio.aoc2018.day12

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PlantPotStateTest {

	@Test
	fun from() {
		val result = PlantPotState.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))

		assertThat(result.states.size, `is`(25))

		assertThat(result.states[0], `is`(true))
		assertThat(result.states[1], `is`(false))
		assertThat(result.states[2], `is`(false))
		assertThat(result.states[3], `is`(true))
		assertThat(result.states[4], `is`(false))
		assertThat(result.states[5], `is`(true))

		assertThat(result.states[20], `is`(false))
		assertThat(result.states[21], `is`(false))
		assertThat(result.states[22], `is`(true))
		assertThat(result.states[23], `is`(true))
		assertThat(result.states[24], `is`(true))
	}

}