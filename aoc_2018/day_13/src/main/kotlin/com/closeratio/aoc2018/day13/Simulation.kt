package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day13.Orientation.*

class Simulation(
		val carts: List<Cart>,
		val tracks: List<Track>
) {

	val trackMap = tracks.associateBy { it.position }
	val gridWidth = trackMap.keys
			.map { it.x }
			.max()!! + 1

	fun iterate(iterationCount: Int?): Vec2i? {
		(1..(iterationCount ?: Int.MAX_VALUE)).forEach {
			val crashLocation = moveCarts()

			if (crashLocation != null) {
				return crashLocation
			}
		}

		return null
	}

	private fun moveCarts(): Vec2i? {
		// Get cart order
		val orderedCarts = carts.sortedBy { it.position.y * gridWidth + it.position.x }

		orderedCarts.forEach {
			it.move(trackMap)

			val crashLocation = findCrashedCartLocation()
			if (crashLocation != null) {
				return crashLocation
			}
		}

		return null
	}

	private fun findCrashedCartLocation(): Vec2i? {
		val cartLocationMap = HashMap<Vec2i, HashSet<Cart>>()
		carts.forEach {
			cartLocationMap.getOrPut(it.position) { hashSetOf() }.add(it)
		}

		return cartLocationMap.filter { it.value.size > 1 }
				.map { it.key }
				.firstOrNull()
	}

	companion object {
		fun from(data: String): Simulation {
			val lines = data.split("\n")

			return Simulation(
					parseCarts(lines),
					parseTracks(lines))
		}

		private fun parseCarts(lines: List<String>): List<Cart> {
			var id = 0

			return lines.mapIndexed { y, line ->
				line.mapIndexedNotNull { x, char ->
					val pos = Vec2i.from(x, y)
					when (char) {
						'>' -> Cart(id++, pos, RIGHT)
						'<' -> Cart(id++, pos, LEFT)
						'v' -> Cart(id++, pos, DOWN)
						'^' -> Cart(id++, pos, UP)
						else -> null
					}
				}
			}.flatten()
		}

		private fun parseTracks(lines: List<String>): List<Track> {
			return lines.mapIndexed { y, line ->
				line.mapIndexedNotNull { x, char ->
					val pos = Vec2i.from(x, y)
					when (char) {
						'/' -> Track(pos, TrackType.FORWARD_SLASH)
						'\\' -> Track(pos, TrackType.BACKWARD_SLASH)
						'|', 'v', '^' -> Track(pos, TrackType.VERTICAL)
						'-', '<', '>' -> Track(pos, TrackType.HORIZONTAL)
						'+' -> Track(pos, TrackType.CROSSROADS)
						else -> null
					}
				}
			}.flatten()
		}
	}
}