package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.math.Vec2i

class FuelCellConfiguration(
		val topLeftCoord: Vec2i,
		val squareSize: Int
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as FuelCellConfiguration

		if (topLeftCoord != other.topLeftCoord) return false
		if (squareSize != other.squareSize) return false

		return true
	}

	override fun hashCode(): Int {
		var result = topLeftCoord.hashCode()
		result = 31 * result + squareSize
		return result
	}

	override fun toString(): String {
		return "FuelCellConfiguration(topLeftCoord=$topLeftCoord, squareSize=$squareSize)"
	}


}