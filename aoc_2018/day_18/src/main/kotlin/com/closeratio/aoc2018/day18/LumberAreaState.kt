package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day18.LumberAreaType.*

class LumberAreaState(
		val cells: Map<Vec2i, LumberAreaType>
) {

	val openGroundCount = cells.values.filter { it == OPEN_GROUND }.size
	val treeCount = cells.values.filter { it == TREES }.size
	val lumberYardCount = cells.values.filter { it == LUMBERYARD }.size
	val resourceValue = treeCount * lumberYardCount

	fun next(): LumberAreaState {
		return LumberAreaState(cells
				.map { entry ->
					val pos = entry.key
					val currType = entry.value
					val surrounding = getSurroundingCells(entry.key)

					val newType = when (currType) {
						OPEN_GROUND -> {
							val treeCount = surrounding.filter { it == TREES }.size
							if (treeCount >= 3) TREES else OPEN_GROUND
						}
						TREES -> {
							val lumberyardCount = surrounding.filter { it == LUMBERYARD }.size
							if (lumberyardCount >= 3) LUMBERYARD else TREES
						}
						LUMBERYARD -> {
							val lumberyardCount = surrounding.filter { it == LUMBERYARD }.size
							val treeCount = surrounding.filter { it == TREES }.size
							if (lumberyardCount >= 1 && treeCount >= 1) LUMBERYARD else OPEN_GROUND
						}
					}

					Pair(pos, newType)
				}
				.toMap())
	}

	private fun getSurroundingCells(pos: Vec2i): List<LumberAreaType> {
		return pos.surrounding()
				.filter { it in cells }
				.map { cells.getValue(it) }
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as LumberAreaState

		if (cells != other.cells) return false

		return true
	}

	override fun hashCode(): Int {
		return cells.hashCode()
	}

	override fun toString(): String {
		return "LumberAreaState(resourceValue=$resourceValue)"
	}

}