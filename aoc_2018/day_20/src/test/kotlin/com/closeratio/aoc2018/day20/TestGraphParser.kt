package com.closeratio.aoc2018.day20

import com.closeratio.aoc2018.day20.GraphParser.parseGraph
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestGraphParser {

    @Test
    fun parseGraph() {
        val smallGraph = parseGraph("^ENWWW(NEEE|SSE(EE|N))\$")
        assertThat(smallGraph.nodes.size, `is`(16))
        assertThat(smallGraph.calculateFurthestNode().doorCount, `is`(10))
        println(smallGraph.printGraph())

        val bigGraph = parseGraph("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))\$")
        assertThat(bigGraph.nodes.size, `is`(49))
        assertThat(bigGraph.calculateFurthestNode().doorCount, `is`(31))
        println(bigGraph.printGraph())

    }

}