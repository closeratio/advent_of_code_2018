package com.closeratio.aoc2018.day2

object InventoryManager {

	fun calculateChecksum(ids: List<String>): Int {
		return ids.map(InventoryManager::calculateInventoryLine)
				.reduce { a, b -> a + b }
				.let { it.pairTotal * it.tripleTotal }
	}

	private fun calculateInventoryLine(line: String): InventoryLine {
		val occurenceMap = hashMapOf<Char, Int>()
		line.forEach {
			val count = occurenceMap.getOrPut(it) { 0 }
			occurenceMap[it] = count + 1
		}

		val pairsPresent = occurenceMap.values.toSet().contains(2)
		val triplesPresent = occurenceMap.values.toSet().contains(3)

		return InventoryLine(
				if (pairsPresent) 1 else 0,
				if (triplesPresent) 1 else 0)
	}

	fun findTargetBoxesSameChars(ids: List<String>): String {
		val resultPair = ids.mapNotNull { id ->
			val match = ids.filter { it != id }
					.map { Pair(it, getCharDifference(id, it)) }
					.firstOrNull { it.second == 1 }
					?.first

			if (match != null) Pair(id, match) else null
		}.first()

		return resultPair.first.mapIndexedNotNull { index, c ->
			if (resultPair.second[index] == c) c else null
		}.joinToString("")
	}

	fun getCharDifference(line1: String, line2: String): Int {
		if (line1.length != line2.length) {
			throw IllegalArgumentException("Lines different lenght")
		}

		return line1.mapIndexed { index, c -> if (line2[index] != c) 1 else 0 }
				.sum()
	}

}