package com.closeratio.aoc2018.day6

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day6.Grid.Companion.from
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class ParseGridTest {

	@Test
	fun input1() {
		val grid = from(loadResource("/test_input_1.txt").data)

		assertThat(grid.coords.size, `is`(6))
		assertThat(grid.coords.map { it.pos }, contains(
				Vec2i(1, 1),
				Vec2i(1, 6),
				Vec2i(8, 3),
				Vec2i(3, 4),
				Vec2i(5, 5),
				Vec2i(8, 9)))

		assertThat(grid.topLeftCorner, `is`(Vec2i(1, 1)))
		assertThat(grid.bottomRightCorner, `is`(Vec2i(8, 9)))
	}

}