package com.closeratio.aoc2018.common.geometry

import com.closeratio.aoc2018.common.math.Vec2i

class BoundingBox private constructor(
		val topLeft: Vec2i,
		val bottomRight: Vec2i,
		val width: Int,
		val height: Int
) {

	val area = width * height

	companion object {

		fun from(vecs: List<Vec2i>): BoundingBox {
			val topLeft = vecs.reduce { acc, v -> Vec2i.from(
					if (acc.x < v.x) acc.x else v.x,
					if (acc.y < v.y) acc.y else v.y)}

			val bottomRight = vecs.reduce { acc, v -> Vec2i.from(
					if (acc.x > v.x) acc.x else v.x,
					if (acc.y > v.y) acc.y else v.y)}

			return from(topLeft, bottomRight)
		}

		fun from(vec: Vec2i) = from(listOf(vec))
		fun from(topLeft: Vec2i, bottomRight: Vec2i): BoundingBox {
			return BoundingBox(
					topLeft,
					bottomRight,
					bottomRight.x - topLeft.x + 1,
					bottomRight.y - topLeft.y + 1)
		}
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as BoundingBox

		if (topLeft != other.topLeft) return false
		if (bottomRight != other.bottomRight) return false

		return true
	}

	override fun hashCode(): Int {
		var result = topLeft.hashCode()
		result = 31 * result + bottomRight.hashCode()
		return result
	}

	override fun toString(): String {
		return "BoundingBox(topLeft=$topLeft, bottomRight=$bottomRight)"
	}

}