package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FuelCellTest {

	@Test
	fun from() {
		val result = FuelCell.from(Vec2i.from(3, 5), 8)

		assertThat(result.powerLevel, `is`(4))
	}

}