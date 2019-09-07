package com.closeratio.aoc2018.day24

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestGroup {

    private val battlefield = BattlefieldFactory.from(javaClass.getResource("/test_input.txt")
            .readText()
            .split("\n")
            .map { it.trim() })

    private val immGroup1 = battlefield.groups[0]
    private val immGroup2 = battlefield.groups[1]
    private val infGroup1 = battlefield.groups[2]
    private val infGroup2 = battlefield.groups[3]

    @Test
    fun effectivePower() {
        assertThat(immGroup1.effectivePower(), `is`(76619))
        assertThat(immGroup2.effectivePower(), `is`(24725))
        assertThat(infGroup1.effectivePower(), `is`(92916))
        assertThat(infGroup2.effectivePower(), `is`(53820))
    }

    @Test
    fun selectTarget() {
        assertThat(immGroup2.selectTarget(battlefield.groups.toSet()), `is`(infGroup1))
    }

    @Test
    fun applyDamage() {
        immGroup2.applyDamage(107640)
        assertThat(immGroup2.initialUnitCount - immGroup2.unitCount, `is`(84))
    }

	@Test
	fun attack() {
		infGroup2.attack(immGroup2)
		assertThat(immGroup2.initialUnitCount - immGroup2.unitCount, `is`(84))

		immGroup2.attack(infGroup1)
		assertThat(infGroup1.initialUnitCount - infGroup1.unitCount, `is`(4))

		immGroup1.attack(infGroup2)
		assertThat(infGroup2.initialUnitCount - infGroup2.unitCount, `is`(51))

		infGroup1.attack(immGroup1)
		assertThat(immGroup1.initialUnitCount - immGroup1.unitCount, `is`(17))
	}

}