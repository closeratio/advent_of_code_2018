package com.closeratio.aoc2018.day10

import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PointTest {

	@Test
	fun move() {
		val point = Point(
				Vec2i.from(0, 0),
				Vec2i.from(2, -4))

		val moved1 = point.move()
		assertThat(moved1.position.x, `is`(2))
		assertThat(moved1.position.y, `is`(-4))

		val moved2 = moved1.move()
		assertThat(moved2.position.x, `is`(4))
		assertThat(moved2.position.y, `is`(-8))

	}

}