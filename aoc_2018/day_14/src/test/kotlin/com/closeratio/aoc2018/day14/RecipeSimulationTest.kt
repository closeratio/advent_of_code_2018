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

	@Test
	fun testStringAppearance51589() {
		assertThat(sim.iterateUntilStringAppears("51589"), `is`(9))
	}

	@Test
	fun testStringAppearance01245() {
		assertThat(sim.iterateUntilStringAppears("01245"), `is`(5))
	}

	@Test
	fun testStringAppearance92510() {
		assertThat(sim.iterateUntilStringAppears("92510"), `is`(18))
	}

	@Test
	fun testStringAppearance59414() {
		assertThat(sim.iterateUntilStringAppears("59414"), `is`(2018))
	}

}