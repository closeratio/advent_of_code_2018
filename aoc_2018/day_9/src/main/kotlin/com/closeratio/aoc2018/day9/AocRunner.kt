package com.closeratio.aoc2018.day9

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result = Game.from(ResourceLoader.loadResource("/input.txt").data).getHighestScore()
		println(result)
	}

}