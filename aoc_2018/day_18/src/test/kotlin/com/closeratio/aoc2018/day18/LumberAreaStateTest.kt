package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day18.LumberAreaParser.parse
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class LumberAreaStateTest {

	val sim = LumberAreaSimulation(parse(ResourceLoader.loadResource("/test_input_1.txt").data))

	@Test
	fun singleIteration() {
		val next = sim.iterate(1)
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_1.txt").data)

		assertThat(next, `is`(expected))
	}

	@Test
	fun twoIterations() {
		val next = sim.iterate(2)
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_2.txt").data)

		assertThat(next, `is`(expected))
	}

	@Test
	fun threeIterations() {
		val next = sim.iterate(3)
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_3.txt").data)

		assertThat(next, `is`(expected))
	}

	@Test
	fun tenIterations() {
		val next = sim.iterate(10)
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_10.txt").data)

		assertThat(next, `is`(expected))
		assertThat(next.resourceValue(), `is`(1147))
	}

}