package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day15.CombatSimulationAttackManipulator.computeOutcome
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CombatSimulationManipulatorTest {

	@Test
	fun computeOutcome1() {
		val result = computeOutcome(ResourceLoader.loadResource("/test_input_2.txt").data)
		assertThat(result, `is`(4988))
	}

	@Test
	fun computeOutcome2() {
		val result = computeOutcome(ResourceLoader.loadResource("/test_input_4.txt").data)
		assertThat(result, `is`(31284))
	}

	@Test
	fun computeOutcome3() {
		val result = computeOutcome(ResourceLoader.loadResource("/test_input_5.txt").data)
		assertThat(result, `is`(3478))
	}

	@Test
	fun computeOutcome4() {
		val result = computeOutcome(ResourceLoader.loadResource("/test_input_6.txt").data)
		assertThat(result, `is`(6474))
	}

	@Test
	fun computeOutcome5() {
		val result = computeOutcome(ResourceLoader.loadResource("/test_input_7.txt").data)
		assertThat(result, `is`(1140))
	}

}