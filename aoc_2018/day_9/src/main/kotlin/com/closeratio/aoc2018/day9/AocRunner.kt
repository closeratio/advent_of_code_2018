package com.closeratio.aoc2018.day9

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result1 = Game.from(ResourceLoader.loadResource("/input_1.txt").data).getHighestScore()
		println(result1)

		val result2 = Game.from(ResourceLoader.loadResource("/input_2.txt").data).getHighestScore()
		println(result2)
	}

}