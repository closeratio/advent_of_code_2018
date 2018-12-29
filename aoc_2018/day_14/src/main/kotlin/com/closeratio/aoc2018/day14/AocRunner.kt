package com.closeratio.aoc2018.day14

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim = RecipeSimulation.create()
		val result = sim.iterateUntilLength(635041)
		println(result)
	}

}