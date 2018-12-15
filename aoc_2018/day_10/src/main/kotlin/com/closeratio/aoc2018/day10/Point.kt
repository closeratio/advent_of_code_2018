package com.closeratio.aoc2018.day10

import com.closeratio.aoc2018.common.math.Vec2i

class Point(
		val position: Vec2i,
		val velocity: Vec2i
) {

	fun move() = Point(position + velocity, velocity)

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Point

		if (position != other.position) return false
		if (velocity != other.velocity) return false

		return true
	}

	override fun hashCode(): Int {
		var result = position.hashCode()
		result = 31 * result + velocity.hashCode()
		return result
	}

	override fun toString(): String {
		return "Point(position=$position, velocity=$velocity)"
	}

}
