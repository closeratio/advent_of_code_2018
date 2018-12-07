package com.closeratio.aoc2018.day4.guard

import com.closeratio.aoc2018.day4.shift.Shift
import java.util.*

class Guard(
		val id: GuardId,
		val shifts: List<Shift>) {

	fun totalSleepTime() = shifts.map { it.sleepTimes.size }.sum()

	fun mostCommonSleepTime(): SleepTimeCount {

		val sleepMap = hashMapOf<Int, Int>()

		shifts.flatMap { shift -> shift.sleepTimes.map { it.minute } }
				.forEach {
					sleepMap.getOrPut(it) { 0 }
					sleepMap[it] = sleepMap[it]!! + 1
				}

		val mostCommonSleepTime = sleepMap.entries.sortedBy { it.value }.lastOrNull() ?: AbstractMap.SimpleEntry(0, 0)

		return SleepTimeCount(mostCommonSleepTime.key, mostCommonSleepTime.value)
	}

	override fun toString(): String {
		return "Guard(id=$id, shifts=$shifts)"
	}


}
