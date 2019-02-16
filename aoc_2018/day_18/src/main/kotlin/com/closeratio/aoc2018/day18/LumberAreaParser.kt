package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.math.Vec2i

object LumberAreaParser {

	fun parse(data: String): LumberAreaState {
		val cellMap = data
				.trim()
				.split("\n")
				.mapIndexed { y, row ->
					row.trim().mapIndexed { x, cellValue -> Pair(
							Vec2i.from(x, y),
							when(cellValue) {
								'.' -> LumberAreaType.OPEN_GROUND
								'|' -> LumberAreaType.TREES
								'#' -> LumberAreaType.LUMBERYARD
								else -> throw IllegalArgumentException("Illegal character: $cellValue")
							})
					}
				}
				.flatten()
				.toMap()

		return LumberAreaState(cellMap)
	}

}