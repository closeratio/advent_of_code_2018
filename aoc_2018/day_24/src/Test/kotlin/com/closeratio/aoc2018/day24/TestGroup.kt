package com.closeratio.aoc2018.day24

import org.junit.jupiter.api.Test

class TestGroup {

    val groups = BattlefieldFactory.from(javaClass.getResource("/test_input.txt")
            .readText()
            .split("\n")
            .map { it.trim() })

    @Test
    fun effectivePower() {

    }

}