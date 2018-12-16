package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class GridParserTest {

	@Test
	fun parseInput1() {
		val squares = ResourceLoader.loadResource("/test_input_1.txt").data
				.split("\n")
				.map { GridParser.parseDef(it.trim()) }

		assertThat(squares.size, `is`(3))

		val square = squares[0]
		assertThat(square.id, `is`(SquareID(1)))
		assertThat(square.topLeftCorner, `is`(Vec2i.from(1, 3)))
		assertThat(square.bottomRightCorner, `is`(Vec2i.from(5, 7)))
		assertThat(square.height, `is`(4))
		assertThat(square.width, `is`(4))
	}

}