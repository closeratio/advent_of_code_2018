package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day17.BlockType.*
import com.closeratio.aoc2018.day17.FillDirection.*
import java.util.*

class WaterSimulation(
		val blockMap: MutableMap<Vec2i, BlockType>
) {

	private val posStack = Stack<Vec2i>()

	private val minY = blockMap.keys
			.map { it.y }
			.min()!!
	private val maxY = blockMap.keys
			.map { it.y }
			.max()!!

	fun simulate() {
		fill(Vec2i.from(500, 1), BOTH)
	}

	private fun fill(pos: Vec2i, direction: FillDirection) {
		if (!isValidFillPosition(pos)) {
			return
		}

		val down = pos.down()
		if (!isPosInsideBounds(down)) {
			blockMap[pos] = FLOWING_WATER
			return
		}

		val downBlock = blockMap[down]
		when (downBlock) {
			null -> {
				fill(down, BOTH)

				val updatedDownBlock = blockMap[down]!!
				if (updatedDownBlock == FLOWING_WATER) {
					blockMap[pos] = FLOWING_WATER
				} else if (updatedDownBlock == SETTLED_WATER) {
					handleSettledFillResult(direction, pos)
				}
			}
			FLOWING_WATER -> blockMap[pos] = FLOWING_WATER
			SETTLED_WATER, CLAY -> {
				handleSettledFillResult(direction, pos)
			}
		}
	}

	private fun handleSettledFillResult(direction: FillDirection, pos: Vec2i) {
		when (direction) {
			BOTH -> {
				fill(pos.left(), LEFT)
				fill(pos.right(), RIGHT)
				reconcileBothFill(pos)
			}
			LEFT -> {
				fill(pos.left(), LEFT)
				blockMap[pos] = if (blockMap[pos.left()] == CLAY) SETTLED_WATER else blockMap[pos.left()]!!
			}
			RIGHT -> {
				fill(pos.right(), RIGHT)
				blockMap[pos] = if (blockMap[pos.right()] == CLAY) SETTLED_WATER else blockMap[pos.right()]!!
			}
		}
	}

	private fun reconcileBothFill(pos: Vec2i) {
		val leftBlock = blockMap[pos.left()]!!
		val rightBlock = blockMap[pos.right()]!!

		if (leftBlock == CLAY && rightBlock == CLAY) {
			blockMap[pos] = SETTLED_WATER
		} else if (leftBlock == CLAY) {
			blockMap[pos] = rightBlock
		} else if (rightBlock == CLAY) {
			blockMap[pos] = leftBlock
		} else if (leftBlock != rightBlock) {
			blockMap[pos] = FLOWING_WATER

			var tempPos = pos.left()
			while (tempPos in blockMap && blockMap[tempPos] == SETTLED_WATER) {
				blockMap[tempPos] = FLOWING_WATER
				tempPos = tempPos.left()
			}

			tempPos = pos.right()
			while (tempPos in blockMap && blockMap[tempPos] == SETTLED_WATER) {
				blockMap[tempPos] = FLOWING_WATER
				tempPos = tempPos.right()
			}
		} else {
			blockMap[pos] = leftBlock
		}
	}

	private fun isValidFillPosition(pos: Vec2i) = isPosInsideBounds(pos) && pos !in blockMap
	private fun isPosInsideBounds(pos: Vec2i) = pos.y <= maxY

	fun waterBlockCount() = blockMap
			.entries
			.filter { it.key.y >= minY }
			.filter { it.key.y <= maxY }
			.filter { it.value == FLOWING_WATER || it.value == SETTLED_WATER }
			.size

	fun serialise(): String {
		val minX = blockMap.keys
				.map { it.x }
				.min()!!

		val maxX = blockMap.keys
				.map { it.x }
				.max()!!

		val sb = StringBuilder()
		(1..maxY).forEach { y ->
			(minX..maxX).forEach { x ->
				val pos = Vec2i.from(x, y)
				sb.append(when (blockMap[pos]) {
					SETTLED_WATER -> "~"
					FLOWING_WATER -> "|"
					CLAY -> "#"
					else -> " "
				})
			}
			sb.appendln()
		}

		return sb.toString()
	}

}