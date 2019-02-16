package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day18.LumberAreaParser.parse
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class LumberAreaStateTest {

	val state = parse(ResourceLoader.loadResource("/test_input_1.txt").data)

	@Test
	fun singleIteration() {
		val next = state.next()
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_1.txt").data)

		assertThat(next, Matchers.`is`(expected))
	}

	@Test
	fun twoIterations() {
		val next = state.next().next()
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_2.txt").data)

		assertThat(next, Matchers.`is`(expected))
	}

	@Test
	fun threeIterations() {
		val next = state.next().next().next()
		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_3.txt").data)

		assertThat(next, Matchers.`is`(expected))
	}

	@Test
	fun tenIterations() {
		val next = state
				.next()
				.next()
				.next()
				.next()
				.next()
				.next()
				.next()
				.next()
				.next()
				.next()

		val expected = parse(ResourceLoader.loadResource("/test_input_1_iteration_10.txt").data)

		assertThat(next, Matchers.`is`(expected))

	}

}