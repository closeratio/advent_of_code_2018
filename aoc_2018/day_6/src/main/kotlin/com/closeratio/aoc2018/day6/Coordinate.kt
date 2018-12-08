package com.closeratio.aoc2018.day6

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

class Coordinate(
		val id: UUID,
		val pos: Vec2i) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Coordinate

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id.hashCode()
	}
}
