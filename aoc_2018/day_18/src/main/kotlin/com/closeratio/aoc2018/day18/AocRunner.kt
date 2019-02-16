package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim = LumberAreaSimulation(LumberAreaParser.parse(ResourceLoader.loadResource("/input.txt").data))
		val endState = sim.iterate(10)
		println(endState.resourceValue())
	}

}