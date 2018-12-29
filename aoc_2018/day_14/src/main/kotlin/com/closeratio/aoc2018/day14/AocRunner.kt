package com.closeratio.aoc2018.day14

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim1 = RecipeSimulation.create()
		val result1 = sim1.iterateUntilLength(635041)
		println(result1)

		val sim2 = RecipeSimulation.create()
		val result2 = sim2.iterateUntilStringAppears("635041")
		println(result2)


	}

}