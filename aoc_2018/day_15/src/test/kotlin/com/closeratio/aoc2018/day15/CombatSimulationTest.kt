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

		val initialPositionMap = sim.entities.associateBy { it.position }

		assertThat(initialPositionMap[Vec2i.from(0, 0)]!! is Rock, `is`(true))
		assertThat(initialPositionMap[Vec2i.from(6, 6)]!! is Rock, `is`(true))

		assertThat(initialPositionMap[Vec2i.from(1, 1)]!! is Goblin, `is`(true))
		assertThat(initialPositionMap[Vec2i.from(1, 3)]!! is Goblin, `is`(true))

		assertThat(initialPositionMap[Vec2i.from(1, 2)]!! is Elf, `is`(true))
		assertThat(initialPositionMap[Vec2i.from(4, 5)]!! is Elf, `is`(true))
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

		println(current.serialise())
		println(expected.serialise())

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
		testExpectedOutcome("/test_input_2.txt", 27730)
	}

	@Test
	fun computeOutcome2() {
		testExpectedOutcome("/test_input_3.txt", 36334)
	}

	@Test
	fun computeOutcome3() {
		testExpectedOutcome("/test_input_4.txt", 39514)
	}

	@Test
	fun computeOutcome4() {
		testExpectedOutcome("/test_input_5.txt", 27755)
	}

	@Test
	fun computeOutcome5() {
		testExpectedOutcome("/test_input_6.txt", 28944)
	}

	@Test
	fun computeOutcome6() {
		testExpectedOutcome("/test_input_7.txt", 18740)
	}

	private fun testExpectedOutcome(inputFile: String, expectedOutcome: Int) {
		val sim = from(ResourceLoader.loadResource(inputFile).data)
		val outcome = sim.computeOutcome()

		println(sim.serialise())

		try {
			assertThat(outcome, `is`(expectedOutcome))
		} catch (err: AssertionError) {
			println(sim.serialise())
			throw err
		}
	}

}