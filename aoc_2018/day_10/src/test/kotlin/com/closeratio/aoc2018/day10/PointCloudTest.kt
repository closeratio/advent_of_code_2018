package com.closeratio.aoc2018.day10

import com.closeratio.aoc2018.common.geometry.BoundingBox
import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PointCloudTest {

	@Test
	fun from() {
		val result = PointCloud.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))

		assertThat(result.points.size, `is`(31))
		assertThat(result.points.first().position, `is`(Vec2i(9, 1)))
		assertThat(result.points.first().velocity, `is`(Vec2i(0, 2)))

		assertThat(result.points.last().position, `is`(Vec2i(-3, 6)))
		assertThat(result.points.last().velocity, `is`(Vec2i(2, -1)))

	}

	@Test
	fun iterate() {
		val result = PointCloud.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))

		result.iterate()
		assertThat(result.points.first().position, `is`(Vec2i(9, 3)))
	}

	@Test
	fun iterateAndDisplayMessage() {
		val pointCloud = PointCloud.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))
		val result = pointCloud.iterateUntilMessageDisplayed()

		val iterCount = result.first
		val bounds = BoundingBox.from(result.second)

		assertThat(iterCount, `is`(3))
		assertThat(bounds.width, `is`(9))
		assertThat(bounds.height, `is`(7))
	}

}