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

}