package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	val state = LumberAreaParser.parse(ResourceLoader.loadResource("/input.txt").data)

	@JvmStatic
	fun main(args: Array<String>) {
		val sim = LumberAreaSimulation(state)
		val endState = sim.iterate(10)
		println(endState.resourceValue)

		val sim2 = LumberAreaSimulation(state)
		val endState2 = sim2.iterate(1000000000)
		println(endState2.resourceValue)
	}

}