package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day15.CombatSimulation.Companion.from
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test

class CombatSimulationTest {

	@Test
	fun from() {
		val sim = from(ResourceLoader.loadResource("/test_input_1.txt").data)

		assertThat(sim.entities.size, `is`(37))
		assertThat(sim.entities.filterIsInstance<Elf>().size, `is`(6))
		assertThat(sim.entities.filterIsInstance<Goblin>().size, `is`(2))
		assertThat(sim.entities.filterIsInstance<Rock>().size, `is`(29))

		assertThat(sim.positionMap[Vec2i.from(0, 0)]!! is Rock, `is`(true))
		assertThat(sim.positionMap[Vec2i.from(6, 6)]!! is Rock, `is`(true))

		assertThat(sim.positionMap[Vec2i.from(1, 1)]!! is Goblin, `is`(true))
		assertThat(sim.positionMap[Vec2i.from(1, 3)]!! is Goblin, `is`(true))

		assertThat(sim.positionMap[Vec2i.from(1, 2)]!! is Elf, `is`(true))
		assertThat(sim.positionMap[Vec2i.from(4, 5)]!! is Elf, `is`(true))
	}

	@Test
	fun iterateSinglePass() {
		val sim = from(ResourceLoader.loadResource("/test_input_2.txt").data)
		val expected = from(ResourceLoader.loadResource("/test_input_2_single_iteration.txt").data)

		sim.iterate()

		expected.positionMap.values.forEach {
			assertThat(sim.positionMap[it.position], notNullValue())
			assertThat(sim.positionMap[it.position]!!.javaClass === it.javaClass, `is`(true))
		}
	}



}