package com.closeratio.aoc2018.day24

import com.closeratio.aoc2018.day24.Allegiance.IMMUNE_SYSTEM
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsCollectionContaining.hasItems
import org.junit.jupiter.api.Test

class TestBattlefieldFactory {

    val battlefield = BattlefieldFactory.from(javaClass
            .getResource("/test_input.txt")
            .readText()
            .split("\n")
            .map { it.trim() })

    @Test
    fun from() {
        val groups = battlefield.groups

        assertThat(groups.size, `is`(4))
        assertThat(groups.filter { it.allegiance == IMMUNE_SYSTEM }.size, `is`(2))

        assertThat(groups[0].initialUnitCount, `is`(17))
        assertThat(groups[0].initialHitPointsPerUnit, `is`(5390))
        assertThat(groups[0].immunities.size, `is`(0))
        assertThat(groups[0].weaknesses, hasItems(DamageType("RADIATION"), DamageType("BLUDGEONING")))
        assertThat(groups[0].attackDamage, `is`(4507))
        assertThat(groups[0].attackDamageType, `is`(DamageType("FIRE")))
        assertThat(groups[0].initiative, `is`(2))

        assertThat(groups[1].weaknesses, hasItems(DamageType("SLASHING"), DamageType("BLUDGEONING")))
        assertThat(groups[1].immunities, hasItems(DamageType("FIRE")))
    }

}