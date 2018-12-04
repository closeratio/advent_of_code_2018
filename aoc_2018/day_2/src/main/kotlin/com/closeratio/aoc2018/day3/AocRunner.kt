package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result1 = InventoryManager.calculateChecksum(
				ResourceLoader.loadResource("/input.txt").data.split("\n"))

		println(result1)

		val result2 = InventoryManager.findTargetBoxesSameChars(
				ResourceLoader.loadResource("/input.txt").data.split("\n"))

		println(result2)
	}

}