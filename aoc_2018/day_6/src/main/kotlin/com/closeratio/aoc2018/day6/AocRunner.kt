package com.closeratio.aoc2018.day6

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val grid = Grid.from(loadResource("/input.txt").data)

		val largestFiniteArea = grid.largestFiniteArea()
		println(largestFiniteArea)

		val safeRegionCount = grid.safeRegionCount(10000)
		println(safeRegionCount)
	}

}