package com.closeratio.aoc2018.day25

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class TestConstellationFactory {

    val constellationSet1 = ConstellationFactory.fromLines(javaClass
            .getResource("/test_input_1.txt")
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })

    @Test
    fun from() {
        assertThat(constellationSet1.size, `is`(4))
    }

}