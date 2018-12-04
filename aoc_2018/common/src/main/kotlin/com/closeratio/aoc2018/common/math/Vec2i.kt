package com.closeratio.aoc2018.common.math

class Vec2i(x: Int, y: Int): Vec2<Int>(x, y) {

	fun left() = Vec2i(x - 1, y)
	fun right() = Vec2i(x + 1, y)
	fun up() = Vec2i(x, y + 1)
	fun down() = Vec2i(x, y - 1)

}