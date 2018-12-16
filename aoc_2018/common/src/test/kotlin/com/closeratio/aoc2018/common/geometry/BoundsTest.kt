package com.closeratio.aoc2018.common.geometry

import com.closeratio.aoc2018.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class BoundsTest {

	@Test
	fun from() {
		val result = BoundingBox.from(listOf(
				Vec2i.from(1, 3),
				Vec2i.from(5, 6),
				Vec2i.from(-1, 4)))

		assertThat(result.topLeft, `is`(Vec2i.from(-1, 3)))
		assertThat(result.bottomRight, `is`(Vec2i.from(5, 6)))
		assertThat(result.width, `is`(6))
		assertThat(result.height, `is`(3))
	}

}