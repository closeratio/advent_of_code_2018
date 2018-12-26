package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day13.Orientation.*

class Simulation(
		val carts: List<Cart>,
		val tracks: List<Track>
) {

	val trackMap = tracks.associateBy { it.position }

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