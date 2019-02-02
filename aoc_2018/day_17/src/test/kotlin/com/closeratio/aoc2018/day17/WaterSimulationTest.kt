package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class WaterSimulationTest {

	val sim = WaterSimulationParser.from(ResourceLoader.loadResource("/test_input_1.txt").data)

	@Test
	fun simulate() {
		sim.simulate()

		println(sim.serialise())

		assertThat(sim.waterBlockCount(), `is`(57))
	}


}