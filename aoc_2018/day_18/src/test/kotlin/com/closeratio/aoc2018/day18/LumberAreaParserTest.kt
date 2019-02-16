package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day18.LumberAreaParser.parse
import com.closeratio.aoc2018.day18.LumberAreaType.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class LumberAreaParserTest {

	@Test
	fun parse() {
		val area = parse(ResourceLoader.loadResource("/test_input_1.txt").data)

		assertThat(area.cells.size, `is`(100))
		assertThat(area.cells[Vec2i.from(0, 0)], `is`(OPEN_GROUND))
		assertThat(area.cells[Vec2i.from(2, 4)], `is`(LUMBERYARD))
		assertThat(area.cells[Vec2i.from(8, 8)], `is`(TREES))
	}

}