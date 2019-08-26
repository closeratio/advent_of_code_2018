package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestRegionMap {

    val regionMap = RegionMap(510, Vec2i.from(10, 10))

    @Test
    fun totalRiskLevel() {
        assertThat(regionMap.totalRiskLevel(), `is`(114))
    }

    @Test
    fun timeToTarget() {
        assertThat(regionMap.timeToTarget(), `is`(45))
    }

}