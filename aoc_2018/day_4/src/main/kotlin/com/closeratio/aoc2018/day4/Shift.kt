package com.closeratio.aoc2018.day4

import java.time.LocalDateTime

class Shift(
		val guardId: GuardId,
		val startTime: LocalDateTime,
		val sleepTimes: List<LocalDateTime>
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Shift

		if (guardId != other.guardId) return false
		if (startTime != other.startTime) return false

		return true
	}

	override fun hashCode(): Int {
		var result = guardId.hashCode()
		result = 31 * result + startTime.hashCode()
		return result
	}

	override fun toString(): String {
		return "Shift(guardId=$guardId, startTime=$startTime, sleepTimes=$sleepTimes)"
	}


}