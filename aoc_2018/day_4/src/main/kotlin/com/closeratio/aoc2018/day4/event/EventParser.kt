package com.closeratio.aoc2018.day4.event

import com.closeratio.aoc2018.day4.Guard
import com.closeratio.aoc2018.day4.GuardId
import java.time.LocalDateTime
import java.util.*

object EventParser {

	private val lineRegex = """^\[(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})\] (.+)$""".toRegex()
	private val guardIdRegex = """#(\d+)""".toRegex()

	fun parseEvent(line: String): Event {

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

	fun parseGuards(events: List<Event>): List<Guard> {
		val eventQueue = LinkedList(events)
		val guards = arrayListOf<Guard>()

		while (eventQueue.isNotEmpty()) {
			val firstEvent = eventQueue.pop() as GuardBeginsShift
			val events = arrayListOf<Event>(firstEvent)

			while (eventQueue.isNotEmpty() && eventQueue.peek() !is GuardBeginsShift) {

			}
		}

		return guards
	}

}