package com.closeratio.aoc2018.day7

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val data = loadResource("/input.txt").data

		val instructions = Instructions.from(data.split("\n"), 60)
		val stepOrder = instructions.stepOrder()
		println(stepOrder)

		val stepOrderParallel = instructions.stepOrderParallel(5)
		println(stepOrderParallel)
	}

}