package com.closeratio.aoc2018.day10

import com.closeratio.aoc2018.common.geometry.BoundingBox
import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

class PointCloud(
		val points: LinkedHashSet<Point>
) {

	fun iterate() {
		val newPOints = points.map { it.move() }
		points.clear()
		points.addAll(newPOints)
	}

	fun iterateUntilMessageDisplayed(): Pair<Int, List<Vec2i>> {

		var iterCount = 0

		var lowestBoundsPositions = points.map { it.position }
		var lowestBounds = BoundingBox.from(lowestBoundsPositions)
		var lowestBoundsFound = false

		// Find positions
		while (!lowestBoundsFound) {
			iterate()
			++iterCount

			val positions = points.map { it.position }
			val bounds = BoundingBox.from(positions)
			if (bounds.height < lowestBounds.height) {
				lowestBoundsPositions = positions
				lowestBounds = bounds
			} else {
				lowestBoundsFound = true
			}

		}

		// Print positions
		val positionSet = lowestBoundsPositions.toSet()
		val sb = StringBuilder()
		IntRange(lowestBounds.topLeft.y, lowestBounds.bottomRight.y).map { y ->
			IntRange(lowestBounds.topLeft.x, lowestBounds.bottomRight.x).map { x ->
				sb.append(if (Vec2i(x, y) in positionSet) "#" else " ")
			}
			sb.appendln()
		}
		println(sb)

		return Pair(
				iterCount - 1,
				lowestBoundsPositions)
	}

	companion object {
		val LINE_REGEX = """position=<\s*(-?\d+),\s*(-?\d+)> velocity=<\s*(-?\d+),\s*(-?\d+)>""".toRegex()

		fun from(lines: List<String>): PointCloud {
			return PointCloud(
					LinkedHashSet(lines.map {
						val matches = LINE_REGEX.find(it)!!.groupValues
						Point(
								Vec2i(matches[1].toInt(), matches[2].toInt()),
								Vec2i(matches[3].toInt(), matches[4].toInt()))
					})
			)
		}
	}
}
