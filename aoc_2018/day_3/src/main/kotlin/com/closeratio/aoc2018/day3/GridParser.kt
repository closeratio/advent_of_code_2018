package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.math.Vec2i

object GridParser {

	private val DEF_REGEX = """^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$""".toRegex()

	fun parseDef(def: String): Square {
		val result = DEF_REGEX.find(def) ?: throw IllegalArgumentException("Def \"$def\" does not match expected pattern")

		val id = SquareID(result.groupValues[1].toInt())
		val topLeft = Vec2i(result.groupValues[2].toInt(), result.groupValues[3].toInt())
		val width = result.groupValues[4].toInt()
		val height = result.groupValues[5].toInt()
		val bottomRight = Vec2i(topLeft.x + width, topLeft.y + height)

		return Square(id, topLeft, bottomRight, width, height)
	}

}