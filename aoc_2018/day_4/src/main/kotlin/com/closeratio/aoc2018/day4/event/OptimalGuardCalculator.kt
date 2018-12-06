package com.closeratio.aoc2018.day4.event

import com.closeratio.aoc2018.day4.Guard
import com.closeratio.aoc2018.day4.GuardId

object OptimalGuardCalculator {

	fun computeOptimalGuardStrategy(guards: List<Guard>): Pair<GuardId, Int> {
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