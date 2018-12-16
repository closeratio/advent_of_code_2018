package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.math.Vec2i

class FuelCell(
		val coordinate: Vec2i,
		val powerLevel: Int
) {


	companion object {
		fun from(coordinate: Vec2i, serialNumber: Int): FuelCell {
			return FuelCell(coordinate,
					computePowerLevel(coordinate, serialNumber))
		}

		private fun computePowerLevel(coordinate: Vec2i, serialNumber: Int): Int {
			val rackId = coordinate.x + 10

			val intermediatePowerLevel = ((rackId * coordinate.y) + serialNumber) * rackId
			if (intermediatePowerLevel < 100) {
				return -5
			}

			val interPowLvlString = intermediatePowerLevel.toString()
			val hundredsDigit = interPowLvlString[interPowLvlString.length - 3]

			return hundredsDigit.toString().toInt() - 5
		}
	}

}
