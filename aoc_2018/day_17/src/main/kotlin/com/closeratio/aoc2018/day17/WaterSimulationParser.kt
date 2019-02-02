package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.math.Vec2i

object WaterSimulationParser {

	val LINE_REGEX = """([xy])=(\d+), ([xy])=(\d+)\.\.(\d+)""".toRegex()

	fun from(data: String): WaterSimulation {
		val blockMap = data.split("\n")
				.map { it.trim() }
				.map { LINE_REGEX.find(it)!! }
				.flatMap { parseBlocks(it) }
				.associate { it }
				.toMutableMap()

		return WaterSimulation(blockMap)
	}

	private fun parseBlocks(matchResult: MatchResult): List<Pair<Vec2i, BlockType>> {
		val firstAxis = matchResult.groupValues[1]
		val firstValue = matchResult.groupValues[2].toInt()

		val secondValueStart = matchResult.groupValues[4].toInt()
		val secondValueEnd = matchResult.groupValues[5].toInt()

		val alongX = firstAxis == "x"

		return (secondValueStart..secondValueEnd).map {
			Pair(
					Vec2i.from(
							if (alongX) firstValue else it,
							if (alongX) it else firstValue),
					BlockType.CLAY)}
	}

}