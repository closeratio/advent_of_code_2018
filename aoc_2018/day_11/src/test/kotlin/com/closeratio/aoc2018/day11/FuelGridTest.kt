package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FuelGridTest {

	@Test
	fun fromWithSerialNumber8() {
		val result = FuelGrid.from(8)
		assertThat(result.cells[Vec2i(3, 5)]!!.powerLevel, `is`(4))
	}

	@Test
	fun fromWithSerialNumber57() {
		val result = FuelGrid.from(57)
		assertThat(result.cells[Vec2i(122, 79)]!!.powerLevel, `is`(-5))
	}

	@Test
	fun fromWithSerialNumber39() {
		val result = FuelGrid.from(39)
		assertThat(result.cells[Vec2i(217, 196)]!!.powerLevel, `is`(0))
	}

	@Test
	fun fromWithSerialNumber71() {
		val result = FuelGrid.from(71)
		assertThat(result.cells[Vec2i(101,153)]!!.powerLevel, `is`(4))
	}

	@Test
	fun largestTotalPowerCoord() {
		val result1 = FuelGrid.from(18)
		assertThat(result1.computeLargestTotalPowerCoord(), `is`(Vec2i(33, 45)))

		val result2 = FuelGrid.from(42)
		assertThat(result2.computeLargestTotalPowerCoord(), `is`(Vec2i(21, 61)))
	}

}