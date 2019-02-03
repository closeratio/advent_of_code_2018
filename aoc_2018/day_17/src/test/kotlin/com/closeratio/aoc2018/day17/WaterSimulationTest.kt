package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class WaterSimulationTest {

	val sim1 = WaterSimulationParser.from(ResourceLoader.loadResource("/test_input_1.txt").data)
	val sim2 = WaterSimulationParser.from(ResourceLoader.loadResource("/test_input_2.txt").data)

	@Test
	fun `Simulate with test input 1`() {
		sim1.simulate()
		assertThat(sim1.waterBlockCount(), `is`(57))
	}

	@Test
	fun `Simulate with test input 2`() {
		sim2.simulate()
		assertThat(sim2.waterBlockCount(), `is`(72))
	}


}