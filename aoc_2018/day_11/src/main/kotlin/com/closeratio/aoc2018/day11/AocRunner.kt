package com.closeratio.aoc2018.day11

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result = FuelGrid.from(8444).computeLargestTotalPowerCoord()
		println(result)
	}

}