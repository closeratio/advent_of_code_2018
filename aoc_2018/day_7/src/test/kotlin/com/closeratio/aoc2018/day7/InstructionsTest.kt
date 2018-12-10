package com.closeratio.aoc2018.day7

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsCollectionContaining
import org.junit.jupiter.api.Test

class InstructionsTest {

	@Test
	fun from() {
		val instructions = Instructions.from(loadResource("/test_input_1.txt").data.split("\n"), 0)

		assertThat(instructions.steps.size, `is`(6))
		assertThat(
				instructions.steps.map { it.name },
				IsCollectionContaining.hasItems('A', 'B', 'C', 'D', 'E', 'F'))

	}

	@Test
	fun stepOrder() {
		val instructions = Instructions.from(loadResource("/test_input_1.txt").data.split("\n"), 0)
		val order = instructions.stepOrder()

		assertThat(order, `is`("CABDFE"))
	}

	@Test
	fun stringValue() {
		println("A".toCharArray()[0].toInt())
		println("B".toCharArray()[0].toInt())
		println("C".toCharArray()[0].toInt())
	}

	@Test
	fun stepOrderParallel() {
		val instructions = Instructions.from(loadResource("/test_input_1.txt").data.split("\n"), 0)
		val order = instructions.stepOrderParallel(2)

		assertThat(order.first, `is`("CABFDE"))
		assertThat(order.second, `is`(15))
	}

}