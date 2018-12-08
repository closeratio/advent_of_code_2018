package com.closeratio.aoc2018.day6

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.junit.jupiter.api.Test

class GridTest {

	@Test
	fun filterCandidateCoords() {
		val grid = Grid.from(loadResource("/test_input_1.txt").data)

		assertThat(grid.filterCandidateCoords().map { it.pos }, containsInAnyOrder(
				Vec2i(3, 4),
				Vec2i(5, 5)))
	}

	@Test
	fun getPoints() {
		val grid = Grid.from(loadResource("/test_input_1.txt").data)

		assertThat(grid.getPoints().size, `is`(72))
	}

	@Test
	fun computeDistanceMap() {
		val grid = Grid.from(loadResource("/test_input_1.txt").data)
		val distMap = grid.computeDistanceMap()

		assertThat(
				distMap[Vec2i(3, 1)]!!.map { it.distance }.min(),
				`is`(2))

		assertThat(
				distMap[Vec2i(2, 9)]!!.map { it.distance }.min(),
				`is`(4))
	}

	@Test
	fun largestFiniteArea() {
		val grid = Grid.from(loadResource("/test_input_1.txt").data)

		assertThat(grid.largestFiniteArea(), `is`(17))
	}

}