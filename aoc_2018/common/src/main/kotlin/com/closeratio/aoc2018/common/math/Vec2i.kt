package com.closeratio.aoc2018.common.math

import java.lang.Math.abs

class Vec2i(x: Int, y: Int): Vec2<Int>(x, y) {

	fun left() = Vec2i(x - 1, y)
	fun right() = Vec2i(x + 1, y)
	fun up() = Vec2i(x, y + 1)
	fun down() = Vec2i(x, y - 1)

	fun manhattan(other: Vec2i) = abs(x - other.x) + abs(y - other.y)

	companion object {
		val ZERO = Vec2i(0, 0)
	}

	operator fun plus(v: Vec2i): Vec2i {
		return Vec2i(x + v.x, y + v.y)
	}

}