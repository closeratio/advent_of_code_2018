package com.closeratio.aoc2018.day4.event

import com.closeratio.aoc2018.day4.guard.GuardId
import java.time.LocalDateTime

object EventFactory {

	private val lineRegex = """^\[(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})] (.+)$""".toRegex()
	private val guardIdRegex = """#(\d+)""".toRegex()

	fun buildEvent(line: String): Event {

		val values = lineRegex.find(line)?.groupValues ?: throw IllegalArgumentException("$line does not match regex")

		val dateTime = LocalDateTime.of(
				values[1].toInt(),
				values[2].toInt(),
				values[3].toInt(),
				values[4].toInt(),
				values[5].toInt())

		return when {
			values[6].endsWith("begins shift") -> GuardBeginsShift(dateTime, GuardId(guardIdRegex.find(values[6])!!.groupValues[1].toInt()))
			values[6] == "falls asleep" -> GuardFallsAsleep(dateTime)
			values[6] == "wakes up" -> GuardWakesUp(dateTime)
			else -> throw IllegalArgumentException("Unknown event: ${values[6]}")
		}
	}

}