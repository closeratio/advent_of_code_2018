package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day17.BlockType.CLAY
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class WaterSimulationParserTest {

	val sim = WaterSimulationParser.from(ResourceLoader.loadResource("/test_input_1.txt").data)

	@Test
	fun from() {
		assertThat(sim.blockMap.values
				.filter { it == CLAY }
				.size,
				`is`(34))
	}

}