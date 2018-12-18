package com.closeratio.aoc2018.day12

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim1 = PlantPotSimulation.from(ResourceLoader.loadResource("/input.txt").data.split("\n"))

		sim1.iterate(20)
		println(sim1.sumOfPlantIndices())

		val sim2 = PlantPotSimulation.from(ResourceLoader.loadResource("/input.txt").data.split("\n"))

		sim2.iterate(50000000000L)
		println(sim2.sumOfPlantIndices())
	}

}