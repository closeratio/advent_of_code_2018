package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day17.BlockType.*
import com.closeratio.aoc2018.day17.FillDirection.*
import java.util.*

class WaterSimulation(
		val blockMap: MutableMap<Vec2i, BlockType>
) {

	private val fillStack = Stack<Pair<Vec2i, FillDirection>>()

	private val minY = blockMap.keys
			.map { it.y }
			.min()!!
	private val maxY = blockMap.keys
			.map { it.y }
			.max()!!

	fun simulate() {
		fillStack.push(Pair(Vec2i.from(500, 1), BOTH))
		fill()
	}

	private fun fill() {
		while (fillStack.isNotEmpty()) {
			val (pos, direction) = fillStack.peek()

			if (!isValidFillPosition(pos)) {
				fillStack.pop()
				continue
			}

			val down = pos.down()
			if (!isPosInsideBounds(down)) {
				blockMap[pos] = FLOWING_WATER
				fillStack.pop()
				continue
			}

			val downBlock = blockMap[down]

			val left = pos.left()
			val leftBlock = blockMap[left]

			val right = pos.right()
			val rightBlock = blockMap[right]

			when (downBlock) {
				null -> fillStack.push(Pair(down, BOTH))
				FLOWING_WATER -> blockMap[pos] = FLOWING_WATER
				SETTLED_WATER, CLAY -> {
					when (direction) {
						BOTH -> {
							if (leftBlock == null || rightBlock == null) {
								fillStack.push(Pair(left, LEFT))
								fillStack.push(Pair(right, RIGHT))
							} else {
								reconcileBothFill(pos)
							}
						}
						LEFT -> {
							if (leftBlock == CLAY || leftBlock == SETTLED_WATER) {
								blockMap[pos] = SETTLED_WATER
								fillStack.pop()
							} else if (leftBlock == FLOWING_WATER) {
								blockMap[pos] = FLOWING_WATER
								fillStack.pop()
							} else {
								fillStack.push(Pair(left, LEFT))
							}
						}
						RIGHT -> {
							if (rightBlock == CLAY || rightBlock == SETTLED_WATER) {
								blockMap[pos] = SETTLED_WATER
								fillStack.pop()
							} else if (rightBlock == FLOWING_WATER) {
								blockMap[pos] = FLOWING_WATER
								fillStack.pop()
							} else {
								fillStack.push(Pair(right, RIGHT))
							}
						}
					}
				}
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

	fun waterBlockCount() = settledWaterBlockCount() + flowingWaterBlockCount()

	private fun flowingWaterBlockCount() = blockMap
			.entries
			.filter { it.value == FLOWING_WATER }
			.filter { it.key.y >= minY }
			.filter { it.key.y <= maxY }
			.size

	fun settledWaterBlockCount() = blockMap
			.entries
			.filter { it.value == SETTLED_WATER }
			.filter { it.key.y >= minY }
			.filter { it.key.y <= maxY }
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