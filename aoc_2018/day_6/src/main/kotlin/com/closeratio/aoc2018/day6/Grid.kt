package com.closeratio.aoc2018.day6

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*
import kotlin.collections.ArrayList

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
		val coordCount = HashMap(coords.associate { Pair(it, 0) })
		val distanceMap = computeDistanceMap()


		distanceMap.values.forEach { coordDistanceSet ->
			val coordDistanceMap = hashMapOf<Int, ArrayList<Coordinate>>()

			coordDistanceSet.forEach {
				coordDistanceMap
						.getOrPut(it.distance) { ArrayList() }
						.add(it.coordinate)
			}

			val lowestDistanceCoords = coordDistanceMap.entries
					.sortedBy { it.key }
					.first()
					.value

			if (lowestDistanceCoords.size == 1) {
				val coord = lowestDistanceCoords[0]
				coordCount[coord] = coordCount[coord]!! + 1
			}
		}

		val applicableCoords = filterCandidateCoords().toSet()

		return coordCount.entries
				.filter { it.key in applicableCoords }
				.sortedBy { it.value }
				.last()
				.value
	}

	fun safeRegionCount(distanceLimit: Int): Int {

		return getPoints().mapNotNull { point ->
			if (coords.map { it.pos.manhattan(point) }.sum() < distanceLimit) {
				1
			} else {
				null
			}
		}.sum()

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