package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim = WaterSimulationParser.from(ResourceLoader.loadResource("/input.txt").data)

		sim.simulate()
		println(sim.serialise())

		println(sim.waterBlockCount())
		println(sim.settledWaterBlockCount())
	}

}