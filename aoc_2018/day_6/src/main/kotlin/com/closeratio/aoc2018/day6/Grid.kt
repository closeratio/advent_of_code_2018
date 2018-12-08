package com.closeratio.aoc2018.day6

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

class Grid(
		val topLeftCorner: Vec2i,
		val bottomRightCorner: Vec2i,
		val coords: List<Coordinate>) {

	fun filterCandidateCoords(): List<Coordinate> {
		return coords.filter {
			it.pos.x > topLeftCorner.x &&
					it.pos.x < bottomRightCorner.x &&
					it.pos.y > topLeftCorner.y &&
					it.pos.y < bottomRightCorner.y
		}
	}

	fun getPoints(): Set<Vec2i> {
		return IntRange(topLeftCorner.x, bottomRightCorner.x).flatMap { x ->
			IntRange(topLeftCorner.y, bottomRightCorner.y).map { y ->
				Vec2i(x, y)
			}
		}.toSet()
	}

	fun computeDistanceMap(): Map<Vec2i, Set<CoordinateDistance>> {
		return getPoints()
				.map { point -> Pair(
						point,
						coords.map { coord ->
							CoordinateDistance(coord, coord.pos.manhattan(point))
						}.toSet())
				}.associate { it }
	}

	fun largestFiniteArea(): Int {
		val candidateCoords = filterCandidateCoords()

		return 0
	}

	companion object {
		fun from(data: String): Grid {
			val points = data.split("\n")
					.map { it.trim() }
					.map {
						val coords = it.split(",")
						Vec2i(coords[0].trim().toInt(), coords[1].trim().toInt())
					}
					.map { Coordinate(UUID.randomUUID(), it) }

			val minX = points.map { it.pos.x }.min()!!
			val minY = points.map { it.pos.y }.min()!!
			val maxX = points.map { it.pos.x }.max()!!
			val maxY = points.map { it.pos.y }.max()!!

			return Grid(
					Vec2i(minX, minY),
					Vec2i(maxX, maxY),
					points)
		}
	}

}