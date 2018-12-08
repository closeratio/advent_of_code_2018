package com.closeratio.aoc2018.day6

class CoordinateDistance(
		val coordinate: Coordinate,
		val distance: Int
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as CoordinateDistance

		if (coordinate != other.coordinate) return false
		if (distance != other.distance) return false

		return true
	}

	override fun hashCode(): Int {
		var result = coordinate.hashCode()
		result = 31 * result + distance
		return result
	}
}