package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CombatSimulationTest {

	@Test
	fun from() {
		val sim = CombatSimulation.from(ResourceLoader.loadResource("/test_input_1.txt").data)

		assertThat(sim.entities.size, `is`(8))
	}

}