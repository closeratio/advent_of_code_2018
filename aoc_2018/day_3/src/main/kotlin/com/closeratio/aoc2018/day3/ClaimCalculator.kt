package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.math.Vec2i

object ClaimCalculator {

	fun calculateOverlappingClaimCount(defs: List<String>): Int {
		val claimMap = HashMap<Vec2i, Int>()

		defs.map { GridParser.parseDef(it) }
				.flatMap { it.points() }
				.forEach {
					claimMap.getOrPut(it) { 0 }
					val count = claimMap[it]!!

					claimMap[it] = count + 1
				}

		return claimMap.values
				.filter { it > 1 }
				.size
	}

}