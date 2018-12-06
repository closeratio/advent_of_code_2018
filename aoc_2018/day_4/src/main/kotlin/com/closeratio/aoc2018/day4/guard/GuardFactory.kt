package com.closeratio.aoc2018.day4.guard

import com.closeratio.aoc2018.day4.shift.Shift

object GuardFactory {

	fun buildGuards(shifts: List<Shift>): List<Guard> {
		val guardMap = HashMap<GuardId, ArrayList<Shift>>()

		shifts.forEach {
			guardMap.getOrPut(it.guardId) { ArrayList() }
			guardMap[it.guardId]!!.add(it)
		}

		return guardMap.map { Guard(it.key, it.value) }
	}

}