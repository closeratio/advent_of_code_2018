package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.geometry.BoundingBox
import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FuelGridTest {

	@Test
	fun fromWithSerialNumber8() {
		val result = FuelGrid.from(8)
		assertThat(result.cells[Vec2i.from(3, 5)]!!.powerLevel, `is`(4))
	}

	@Test
	fun fromWithSerialNumber57() {
		val result = FuelGrid.from(57)
		assertThat(result.cells[Vec2i.from(122, 79)]!!.powerLevel, `is`(-5))
	}

	@Test
	fun fromWithSerialNumber39() {
		val result = FuelGrid.from(39)
		assertThat(result.cells[Vec2i.from(217, 196)]!!.powerLevel, `is`(0))
	}

	@Test
	fun fromWithSerialNumber71() {
		val result = FuelGrid.from(71)
		assertThat(result.cells[Vec2i.from(101,153)]!!.powerLevel, `is`(4))
	}

	@Test
	fun largestTotalPowerCoord() {
		val result1 = FuelGrid.from(18)
		assertThat(result1.computeLargestTotalPowerCoord(3, 3).topLeftCoord, `is`(Vec2i.from(33, 45)))

		val result2 = FuelGrid.from(42)
		assertThat(result2.computeLargestTotalPowerCoord(3, 3).topLeftCoord, `is`(Vec2i.from(21, 61)))
	}

	@Test
	fun getBoundingBoxes() {
		val grid = FuelGrid.from(1)

		val result1 = grid.getBoundingBoxes(BoundingBox.from(
				Vec2i.from(1, 1), Vec2i.from(3, 1)))

		assertThat(result1.size, `is`(2))
		assertThat(result1[0], `is`(BoundingBox.from(Vec2i.from(1, 1))))
		assertThat(result1[1], `is`(BoundingBox.from(Vec2i.from(2, 1), Vec2i.from(3, 1))))

		val result2 = grid.getBoundingBoxes(BoundingBox.from(
				Vec2i.from(4, 5), Vec2i.from(4, 9)))
		assertThat(result2.size, `is`(2))
		assertThat(result2[0], `is`(BoundingBox.from(Vec2i.from(4, 5), Vec2i.from(4, 6))))
		assertThat(result2[1], `is`(BoundingBox.from(Vec2i.from(4, 7), Vec2i.from(4, 9))))

		val result3 = grid.getBoundingBoxes(BoundingBox.from(
				Vec2i.from(5, 2), Vec2i.from(9, 6)))
		assertThat(result3.size, `is`(4))
		assertThat(result3[0], `is`(BoundingBox.from(Vec2i.from(5, 2), Vec2i.from(6, 3))))
		assertThat(result3[1], `is`(BoundingBox.from(Vec2i.from(7, 2), Vec2i.from(9, 3))))
		assertThat(result3[2], `is`(BoundingBox.from(Vec2i.from(5, 4), Vec2i.from(6, 6))))
		assertThat(result3[3], `is`(BoundingBox.from(Vec2i.from(7, 4), Vec2i.from(9, 6))))

		val result4 = grid.getBoundingBoxes(BoundingBox.from(
				Vec2i.from(6, 3), Vec2i.from(7, 3)))
		assertThat(result4.size, `is`(2))
		assertThat(result4[0], `is`(BoundingBox.from(Vec2i.from(6, 3))))
		assertThat(result4[1], `is`(BoundingBox.from(Vec2i.from(7, 3))))
	}

}