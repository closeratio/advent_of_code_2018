package com.closeratio.aoc2018.day12

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PlantPotSimulationTest {

	private val sim = PlantPotSimulation.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))

	@Test
	fun sumOfPlantIndicies() {
		sim.iterate(20)

		assertThat(sim.sumOfPlantIndices(), `is`(325))
	}
}