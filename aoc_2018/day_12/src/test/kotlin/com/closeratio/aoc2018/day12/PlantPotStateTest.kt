package com.closeratio.aoc2018.day12

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PlantPotStateTest {

	@Test
	fun from() {
		val result = PlantPotState.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))

		assertThat(result.plantPots.size, `is`(25))

		assertThat(result.plantPots[0], `is`(true))
		assertThat(result.plantPots[1], `is`(false))
		assertThat(result.plantPots[2], `is`(false))
		assertThat(result.plantPots[3], `is`(true))
		assertThat(result.plantPots[4], `is`(false))
		assertThat(result.plantPots[5], `is`(true))

		assertThat(result.plantPots[20], `is`(false))
		assertThat(result.plantPots[21], `is`(false))
		assertThat(result.plantPots[22], `is`(true))
		assertThat(result.plantPots[23], `is`(true))
		assertThat(result.plantPots[24], `is`(true))
	}

}