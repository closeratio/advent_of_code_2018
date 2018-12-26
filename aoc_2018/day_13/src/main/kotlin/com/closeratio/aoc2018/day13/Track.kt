package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i

class Track(
		val position: Vec2i,
		val trackType: TrackType
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Track

		if (position != other.position) return false

		return true
	}

	override fun hashCode(): Int {
		return position.hashCode()
	}

	override fun toString(): String {
		return "Track(position=$position, trackType=$trackType)"
	}

}