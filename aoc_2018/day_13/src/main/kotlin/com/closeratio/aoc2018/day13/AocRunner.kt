package com.closeratio.aoc2018.day13

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim = Simulation.from(javaClass.getResource("/input.txt").readText())

		val result = sim.iterate(null)
		println(result)
	}

}