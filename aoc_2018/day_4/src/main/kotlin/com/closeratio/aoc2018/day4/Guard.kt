package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.day4.event.*
import java.time.LocalDateTime
import java.util.*

class Guard(
		val id: GuardId,
		val events: List<Event>
) {

	val shifts = computeShifts()

	private fun computeShifts(): List<Shift> {
		val eventQueue = LinkedList(events.sortedBy { it.dateTime })
		val shifts = arrayListOf<Shift>()

		while (eventQueue.isNotEmpty()) {
			val startEvent = eventQueue.pop() as GuardBeginsShift
			val sleepTimes = arrayListOf<LocalDateTime>()

			while (eventQueue.peek() !is GuardBeginsShift) {
				val sleepStart = (eventQueue.pop() as GuardFallsAsleep).dateTime
				val sleepEnd = (eventQueue.pop() as GuardWakesUp).dateTime

				var currTime = sleepStart
				while (currTime != sleepEnd) {
					sleepTimes.add(currTime)

					currTime = currTime.plusMinutes(1)
				}
			}

			shifts.add(Shift(
					id,
					startEvent.dateTime,
					sleepTimes))
		}

		return shifts
	}

}
