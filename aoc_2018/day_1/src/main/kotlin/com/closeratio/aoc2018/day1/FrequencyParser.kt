package com.closeratio.aoc2018.day1

object FrequencyParser {

	fun parseFrequencies(data: String): Int {
		return data.split("\n")
				.map { it.trim() }
				.filter { it.isNotBlank() }
				.map { Integer.parseInt(it) }
				.sum()
	}

	fun computeRepeatedFrequency(data: String): Int {
		val sequence = data.split("\n")
				.map { it.trim() }
				.filter { it.isNotBlank() }
				.map { Integer.parseInt(it) }

		var frequency = 0
		var index = 0
		val frequencies = hashSetOf(0)

		while (true) {
			frequency += sequence[index % sequence.size]

			if (!frequencies.add(frequency)) {
				return frequency
			}

			++index
		}
	}

}