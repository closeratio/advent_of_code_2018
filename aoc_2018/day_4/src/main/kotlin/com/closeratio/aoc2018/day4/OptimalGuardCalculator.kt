package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.day4.event.EventFactory.buildEvent
import com.closeratio.aoc2018.day4.guard.GuardFactory.buildGuards
import com.closeratio.aoc2018.day4.guard.GuardId
import com.closeratio.aoc2018.day4.shift.ShiftFactory.buildShifts

object OptimalGuardCalculator {

	fun computeOptimalGuardStrategyBasic(lines: List<String>): Pair<GuardId, Int> {
		val guards = buildGuards(buildShifts(lines.map { buildEvent(it) }))

		val targetGuard = guards.sortedBy { it.totalSleepTime() }.last()

		return Pair(targetGuard.id, targetGuard.mostCommonSleepTime().minute)
	}

	fun computeOptimalGuardStrategyAdvanced(lines: List<String>): Pair<GuardId, Int> {
		val guards = buildGuards(buildShifts(lines.map { buildEvent(it) }))

		val targetGuard = guards.map { Pair(it.id, it.mostCommonSleepTime()) }
				.sortedBy { it.second.count }
				.last()

		return Pair(targetGuard.first, targetGuard.second.minute)
	}

}