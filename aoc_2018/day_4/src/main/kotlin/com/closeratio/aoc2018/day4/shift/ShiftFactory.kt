package com.closeratio.aoc2018.day4.shift

import com.closeratio.aoc2018.day4.event.Event
import com.closeratio.aoc2018.day4.event.GuardBeginsShift
import com.closeratio.aoc2018.day4.event.GuardFallsAsleep
import com.closeratio.aoc2018.day4.event.GuardWakesUp
import java.time.LocalDateTime
import java.util.*

object ShiftFactory {

	fun buildShifts(events: List<Event>): List<Shift> {
		val eventQueue = LinkedList(events.sortedBy { it.dateTime })
		val shiftList = arrayListOf<Shift>()

		while (eventQueue.isNotEmpty()) {
			val start = eventQueue.pop() as GuardBeginsShift
			val sleepTimes = arrayListOf<LocalDateTime>()

			while (eventQueue.isNotEmpty() && eventQueue.peek() !is GuardBeginsShift) {
				val sleepStart = (eventQueue.pop() as GuardFallsAsleep).dateTime
				val sleepEnd = (eventQueue.pop() as GuardWakesUp).dateTime

				var currTime = sleepStart
				while (currTime != sleepEnd) {
					sleepTimes.add(currTime)
					currTime = currTime.plusMinutes(1)
				}
			}

			shiftList.add(Shift(start.guardId, start.dateTime, sleepTimes))
		}

		return shiftList
	}

}