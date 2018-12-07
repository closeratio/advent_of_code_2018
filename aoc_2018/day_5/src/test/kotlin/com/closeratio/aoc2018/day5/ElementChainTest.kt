package com.closeratio.aoc2018.day5

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ElementChainTest {

	@Test
	fun input1Parse() {
		val result = ElementChain.from(ResourceLoader.loadResource("/test_input_1.txt").data)

		assertThat(result.elements.map { it.value }, `is`(listOf(
				'd', 'a', 'b', 'A',
				'c', 'C', 'a', 'C',
				'B', 'A', 'c', 'C',
				'c', 'a', 'D', 'A')))
	}

	@Test
	fun input1React() {
		val loaded = ElementChain.from(ResourceLoader.loadResource("/test_input_1.txt").data)
		val result = loaded.react()

		assertThat(result.elements.map { it.value }.joinToString(""), `is`("dabCBAcaDA"))
	}

}