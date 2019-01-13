package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day15.CombatSimulation.Companion.from
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CombatSimulationTest {

	@Test
	fun from() {
		val sim = from(ResourceLoader.loadResource("/test_input_1.txt").data)

		assertThat(sim.entities.size, `is`(37))
		assertThat(sim.entities.filterIsInstance<Elf>().size, `is`(6))
		assertThat(sim.entities.filterIsInstance<Goblin>().size, `is`(2))
		assertThat(sim.entities.filterIsInstance<Rock>().size, `is`(29))

		assertThat(sim.initialPositionMap[Vec2i.from(0, 0)]!! is Rock, `is`(true))
		assertThat(sim.initialPositionMap[Vec2i.from(6, 6)]!! is Rock, `is`(true))

		assertThat(sim.initialPositionMap[Vec2i.from(1, 1)]!! is Goblin, `is`(true))
		assertThat(sim.initialPositionMap[Vec2i.from(1, 3)]!! is Goblin, `is`(true))

		assertThat(sim.initialPositionMap[Vec2i.from(1, 2)]!! is Elf, `is`(true))
		assertThat(sim.initialPositionMap[Vec2i.from(4, 5)]!! is Elf, `is`(true))
	}

	@Test
	fun singleIteration() {
		val sim = from(ResourceLoader.loadResource("/test_input_2.txt").data)

		val expected = from(ResourceLoader.loadResource("/test_input_2_single_iteration.txt").data)
		val expCombatEntities = expected.entities
				.filterIsInstance<CombatEntity>()
				.sortedBy { it.orderValue(Vec2i.from(7, 7)) }
		(expCombatEntities[1] as Elf).currentHealth -= 3
		(expCombatEntities[2] as Goblin).currentHealth -= 3
		(expCombatEntities[4] as Goblin).currentHealth -= 3
		(expCombatEntities[5] as Elf).currentHealth -= 3

		sim.iterate()

		compareSimulations(sim, expected)
	}

	@Test
	fun twoIterations() {
		val sim = from(ResourceLoader.loadResource("/test_input_2.txt").data)

		val expected = from(ResourceLoader.loadResource("/test_input_2_two_iterations.txt").data)
		val expCombatEntities = expected.entities
				.filterIsInstance<CombatEntity>()
				.sortedBy { it.orderValue(Vec2i.from(7, 7)) }
		(expCombatEntities[2] as Elf).currentHealth -= 12
		(expCombatEntities[3] as Goblin).currentHealth -= 6
		(expCombatEntities[4] as Goblin).currentHealth -= 6
		(expCombatEntities[5] as Elf).currentHealth -= 6

		sim.iterate()
		sim.iterate()

		compareSimulations(sim, expected)
	}

	@Test
	fun `23 Iterations`() {
		val sim = from(ResourceLoader.loadResource("/test_input_2.txt").data)

		val expected = from(ResourceLoader.loadResource("/test_input_2_23_iterations.txt").data)
		val expCombatEntities = expected.entities
				.filterIsInstance<CombatEntity>()
				.sortedBy { it.orderValue(Vec2i.from(7, 7)) }
		(expCombatEntities[2] as Goblin).currentHealth -= 69
		(expCombatEntities[3] as Goblin).currentHealth -= 69
		(expCombatEntities[4] as Elf).currentHealth -= 69

		(1..23).forEach { sim.iterate() }

		compareSimulations(sim, expected)
	}

	@Test
	fun `47 Iterations`() {
		val sim = from(ResourceLoader.loadResource("/test_input_2.txt").data)

		val expected = from(ResourceLoader.loadResource("/test_input_2_47_iterations.txt").data)
		val expCombatEntities = expected.entities
				.filterIsInstance<CombatEntity>()
				.sortedBy { it.orderValue(Vec2i.from(7, 7)) }
		(expCombatEntities[1] as Goblin).currentHealth -= 69
		(expCombatEntities[2] as Goblin).currentHealth -= 141

		(1..47).forEach { sim.iterate() }

		compareSimulations(sim, expected)
	}

	private fun compareSimulations(current: CombatSimulation, expected: CombatSimulation) {
		val simEntityMap = current.entities.associateBy { it.position }

		assertThat(current.entities.size, `is`(expected.entities.size))

		expected.entities.forEach { expEntity: Entity ->
			val currEnt = simEntityMap[expEntity.position]!!

			assertThat(currEnt, notNullValue())
			assertTrue(currEnt.javaClass == expEntity.javaClass)
			if (currEnt is CombatEntity) {
				expEntity as CombatEntity
				assertThat(currEnt.currentHealth, `is`(expEntity.currentHealth))
			}
		}
	}

	@Test
	fun computeOutcome1() {
		val sim = from(ResourceLoader.loadResource("/test_input_2.txt").data)

		val outcome = sim.computeOutcome()

		assertThat(outcome, `is`(27730))
	}

	@Test
	fun computeOutcome2() {
		val sim = from(ResourceLoader.loadResource("/test_input_3.txt").data)

		val outcome = sim.computeOutcome()

		assertThat(outcome, `is`(36334))
	}

	@Test
	fun computeOutcome3() {
		val sim = from(ResourceLoader.loadResource("/test_input_4.txt").data)

		val outcome = sim.computeOutcome()

		assertThat(outcome, `is`(39514))
	}

	@Test
	fun computeOutcome4() {
		val sim = from(ResourceLoader.loadResource("/test_input_5.txt").data)

		val outcome = sim.computeOutcome()

		assertThat(outcome, `is`(27755))
	}

	@Test
	fun computeOutcome5() {
		val sim = from(ResourceLoader.loadResource("/test_input_6.txt").data)

		val outcome = sim.computeOutcome()

		assertThat(outcome, `is`(28944))
	}

	@Test
	fun computeOutcome6() {
		val sim = from(ResourceLoader.loadResource("/test_input_7.txt").data)

		val outcome = sim.computeOutcome()

		assertThat(outcome, `is`(18740))
	}

}