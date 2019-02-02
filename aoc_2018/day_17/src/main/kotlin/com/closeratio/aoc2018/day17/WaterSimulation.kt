package com.closeratio.aoc2018.day17

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day17.BlockType.*

class WaterSimulation(
		val blockMap: MutableMap<Vec2i, BlockType>
) {

	private val minX = blockMap.keys
			.map { it.x }
			.min()!!
	private val minY = blockMap.keys
			.map { it.y }
			.min()!!

	private val maxX = blockMap.keys
			.map { it.x }
			.max()!!
	private val maxY = blockMap.keys
			.map { it.y }
			.max()!!

	fun simulate() {
		iterateRecursive(Vec2i.from(500, 0))
	}

	private fun iterateRecursive(pos: Vec2i) {
		if (pos.y > maxY) {
			return
		}

		if (pos in blockMap) {
			return
		}

		val next = pos.down()
		if (next !in blockMap) {
			iterateRecursive(next)
		}

		if (blockMap[next] == SETTLED_WATER || blockMap[next] == CLAY) {
			fillRow(pos)
		} else {
			blockMap[pos] = FLOWING_WATER
		}
	}

	private fun fillRow(pos: Vec2i) {
		val (hasWalls, leftExtent, rightExtent) = checkForWalls(pos)

		(leftExtent.x..pos.x).forEach {
			blockMap[Vec2i.from(it, pos.y)] = if (hasWalls) SETTLED_WATER else FLOWING_WATER
		}

		(pos.x..rightExtent.x).forEach {
			blockMap[Vec2i.from(it, pos.y)] = if (hasWalls) SETTLED_WATER else FLOWING_WATER
		}

		val leftDown = leftExtent.down()
		if (leftDown !in blockMap) {
			iterateRecursive(leftDown)
		}

		val rightDown = rightExtent.down()
		if (rightDown !in blockMap) {
			iterateRecursive(rightDown)
		}

		if (!hasWalls && blockMap[leftDown] == SETTLED_WATER && blockMap[rightDown] == SETTLED_WATER) {
			fillRow(pos)
		}
	}


	private fun checkForWalls(pos: Vec2i): Triple<Boolean, Vec2i, Vec2i> {
		var walls = true
		var leftExtent: Vec2i? = null
		var rightExtent: Vec2i? = null

		var currPos = pos
		while (leftExtent == null) {
			when {
				currPos.down() !in blockMap -> {
					leftExtent = currPos
					walls = false
				}
				currPos.left() in blockMap -> leftExtent = currPos
				else -> currPos = currPos.left()
			}
		}

		currPos = pos
		while (rightExtent == null) {
			when {
				currPos.down() !in blockMap -> {
					rightExtent = currPos
					walls = false
				}
				currPos.right() in blockMap -> rightExtent = currPos
				else -> currPos = currPos.right()
			}
		}

		return Triple(walls, leftExtent, rightExtent)
	}

	fun waterBlockCount() = blockMap
			.entries
			.filter { it.key.y >= minY }
			.filter { it.key.y <= maxY }
			.filter { it.value == FLOWING_WATER || it.value == SETTLED_WATER }
			.size

	fun serialise(): String {
		val sb = StringBuilder()
		(minY..maxY).forEach { y ->
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