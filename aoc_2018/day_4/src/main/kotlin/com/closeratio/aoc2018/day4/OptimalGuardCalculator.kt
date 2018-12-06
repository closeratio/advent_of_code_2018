package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.day4.event.EventFactory.buildEvent
import com.closeratio.aoc2018.day4.guard.GuardFactory.buildGuards
import com.closeratio.aoc2018.day4.guard.GuardId
import com.closeratio.aoc2018.day4.shift.ShiftFactory.buildShifts

object OptimalGuardCalculator {

	fun computeOptimalGuardStrategy(lines: List<String>): Pair<GuardId, Int> {
		val events = lines.map { buildEvent(it) }
		val shifts = buildShifts(events)
		val guards = buildGuards(shifts)

		val targetGuard = guards.sortedBy { it.shifts.flatMap { it.sleepTimes }.size }.last()

		val sleepMap = hashMapOf<Int, Int>()

		targetGuard.shifts
				.flatMap { shift -> shift.sleepTimes.map { it.minute } }
				.forEach {
					sleepMap.getOrPut(it) { 0 }
					sleepMap[it] = sleepMap[it]!! + 1
				}

		return Pair(targetGuard.id, sleepMap.entries.sortedBy { it.value }.last().key)
	}

}