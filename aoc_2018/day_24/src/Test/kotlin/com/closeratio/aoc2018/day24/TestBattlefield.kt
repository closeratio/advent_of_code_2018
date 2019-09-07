package com.closeratio.aoc2018.day24

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Test

class TestBattlefield {

    private val battlefield = BattlefieldFactory.from(javaClass.getResource("/test_input.txt")
            .readText()
            .split("\n")
            .map { it.trim() })

    private val immGroup1 = battlefield.groups[0]
    private val immGroup2 = battlefield.groups[1]
    private val infGroup1 = battlefield.groups[2]
    private val infGroup2 = battlefield.groups[3]

    @Test
    fun selectTargets() {
        val selections = battlefield.selectTargets()
        assertThat(selections.size, `is`(4))

        assertThat(selections, hasItem(infGroup2 to immGroup2))
        assertThat(selections, hasItem(immGroup2 to infGroup1))
        assertThat(selections, hasItem(immGroup1 to infGroup2))
        assertThat(selections, hasItem(infGroup1 to immGroup1))
    }

    @Test
    fun fight() {
        battlefield.fight()

        assertThat(immGroup2.initialUnitCount - immGroup2.unitCount, `is`(84))
        assertThat(infGroup1.initialUnitCount - infGroup1.unitCount, `is`(4))
        assertThat(infGroup2.initialUnitCount - infGroup2.unitCount, `is`(51))
        assertThat(immGroup1.initialUnitCount - immGroup1.unitCount, `is`(17))
    }

    @Test
    fun fightUntilFinished() {
        val unitCount = battlefield.fightUntilFinished()!!.unitCount

        assertThat(unitCount, `is`(5216))
    }
}