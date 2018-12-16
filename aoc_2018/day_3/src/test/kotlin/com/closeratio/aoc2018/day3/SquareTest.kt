package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class SquareTest {

	@Test
	fun testPoints() {
		val points = Square(SquareID(0), Vec2i.from(1, 3), Vec2i.from(5, 7), 4, 4).points()

		assertThat(points.size, `is`(16))
	}

}