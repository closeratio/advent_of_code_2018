package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.math.Vec2i

class Square(
		val id: SquareID,
		val topLeftCorner: Vec2i,
		val bottomRightCorner: Vec2i,
		val width: Int,
		val height: Int) {

	fun points(): Set<Vec2i> {
		return IntRange(topLeftCorner.x, topLeftCorner.x + width - 1).flatMap { x ->
			IntRange(topLeftCorner.y, topLeftCorner.y + height - 1).map { y ->
				Vec2i.from(x, y)
			}
		}.toSet()
	}

}