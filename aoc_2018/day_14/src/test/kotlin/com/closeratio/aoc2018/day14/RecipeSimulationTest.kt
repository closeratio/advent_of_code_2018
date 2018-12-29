package com.closeratio.aoc2018.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class RecipeSimulationTest {

	private val sim = RecipeSimulation.create()

	@Test
	fun test9Recipes() {
		assertThat(sim.iterateUntilLength(9), `is`("5158916779"))
	}

	@Test
	fun test5Recipes() {
		assertThat(sim.iterateUntilLength(5), `is`("0124515891"))
	}

	@Test
	fun test18Recipes() {
		assertThat(sim.iterateUntilLength(18), `is`("9251071085"))
	}

	@Test
	fun test2018Recipes() {
		assertThat(sim.iterateUntilLength(2018), `is`("5941429882"))
	}

}