package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.math.Vec2i

class FuelGrid(
		val cells: Map<Vec2i, FuelCell>
) {

	fun computeLargestTotalPowerCoord(): Vec2i {
		val powerLevelList = IntRange(1, 298).flatMap { x ->
			IntRange(1, 298).map { y ->
				val coord = Vec2i(x, y)
				val powerLevel = IntRange(0, 2).flatMap { xOffset ->
					IntRange(0, 2).map { yOffset ->
						cells[Vec2i(x + xOffset, y + yOffset)]!!.powerLevel
					}
				}.sum()

				Pair(coord, powerLevel)
			}
		}.sortedBy { it.second }

		return powerLevelList.last().first
	}

	companion object {
		fun from(serialNumber: Int): FuelGrid {
			return FuelGrid(
					IntRange(1, 300).flatMap { y ->
						IntRange(1, 300).map { x ->
							FuelCell.from(Vec2i(x, y), serialNumber)
						}
					}.associateBy { it.coordinate })
		}
	}
}