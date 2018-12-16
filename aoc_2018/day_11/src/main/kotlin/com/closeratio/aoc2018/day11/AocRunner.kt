package com.closeratio.aoc2018.day11

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val result1 = FuelGrid.from(8444).computeLargestTotalPowerCoord(3, 3)
		println(result1)

		val result2 = FuelGrid.from(8444).computeLargestTotalPowerCoord(1, 25)
		println(result2)
	}

}