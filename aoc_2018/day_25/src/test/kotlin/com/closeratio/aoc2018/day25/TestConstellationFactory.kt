package com.closeratio.aoc2018.day25

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class TestConstellationFactory {

    private fun loadConstellation(resource: String) = ConstellationFactory.fromLines(javaClass
            .getResource(resource)
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })

    private val constellationSet1 = loadConstellation("/test_input_1.txt")
    private val constellationSet2 = loadConstellation("/test_input_2.txt")
    private val constellationSet3 = loadConstellation("/test_input_3.txt")

    @Test
    fun from() {
        assertThat(constellationSet1.size, `is`(4))
        assertThat(constellationSet2.size, `is`(3))
        assertThat(constellationSet3.size, `is`(8))
    }

}