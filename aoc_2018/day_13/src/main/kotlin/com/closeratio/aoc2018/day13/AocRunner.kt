package com.closeratio.aoc2018.day13

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val sim1 = Simulation.from(javaClass.getResource("/input.txt").readText())
		val result1 = sim1.iterateUntilCrash()
		println(result1)

		val sim2 = Simulation.from(javaClass.getResource("/input.txt").readText())
		val result2 = sim2.iterateUntilSingleCartLeft()
		println(result2)
	}

}