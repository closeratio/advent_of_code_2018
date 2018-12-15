package com.closeratio.aoc2018.common.geometry

import com.closeratio.aoc2018.common.math.Vec2i

class BoundingBox(
		val topLeft: Vec2i,
		val bottomRight: Vec2i,
		val width: Int,
		val height: Int
) {

	val area = width * height

	companion object {
		fun from(vecs: List<Vec2i>): BoundingBox {
			val topLeft = vecs.reduce { acc, v -> Vec2i(
					if (acc.x < v.x) acc.x else v.x,
					if (acc.y < v.y) acc.y else v.y)}

			val bottomRight = vecs.reduce { acc, v -> Vec2i(
					if (acc.x > v.x) acc.x else v.x,
					if (acc.y > v.y) acc.y else v.y)}

			return BoundingBox(
					topLeft,
					bottomRight,
					bottomRight.x - topLeft.x,
					bottomRight.y - topLeft.y)
		}
	}
}