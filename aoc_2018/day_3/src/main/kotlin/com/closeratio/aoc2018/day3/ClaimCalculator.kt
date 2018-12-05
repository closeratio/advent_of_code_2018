package com.closeratio.aoc2018.day3

import com.closeratio.aoc2018.common.math.Vec2i

object ClaimCalculator {

	private fun calculateClaimMap(defs: List<String>): Map<Vec2i, HashSet<SquareID>> {
		val claimMap = HashMap<Vec2i, HashSet<SquareID>>()

		defs.map { GridParser.parseDef(it) }
				.flatMap { square -> square.points().map { Pair(square.id, it) } }
				.forEach {
					claimMap.getOrPut(it.second) { HashSet() }.add(it.first)
				}

		return claimMap
	}

	fun calculateOverlappingClaimCount(defs: List<String>) = calculateClaimMap(defs)
			.values
			.filter { it.size > 1 }
			.size

	fun calculateSingleClaimSquareID(defs: List<String>): Set<SquareID> {
		val claimMap = calculateClaimMap(defs)

		val singleClaimIDs = claimMap.values
				.flatten()
				.toHashSet()

		claimMap.values
				.filter { it.size > 1 }
				.flatten()
				.forEach {
					singleClaimIDs.remove(it)
				}

		return singleClaimIDs
	}

}