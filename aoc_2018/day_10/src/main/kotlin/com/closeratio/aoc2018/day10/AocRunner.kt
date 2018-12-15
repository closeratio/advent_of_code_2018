package com.closeratio.aoc2018.day10

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val pointCloud = PointCloud.from(ResourceLoader.loadResource("/input.txt").data.split("\n"))

		val result = pointCloud.iterateUntilMessageDisplayed()
		println(result.first)
	}

}