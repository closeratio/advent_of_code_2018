package com.closeratio.aoc2018.day1

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day1.FrequencyParser.computeRepeatedFrequency
import com.closeratio.aoc2018.day1.FrequencyParser.parseFrequencies

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val data = loadResource("/input.txt").data

		println(parseFrequencies(data))
		println(computeRepeatedFrequency(data))
	}

}