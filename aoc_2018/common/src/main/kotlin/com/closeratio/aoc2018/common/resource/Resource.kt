package com.closeratio.aoc2018.common.resource

class Resource(
		val data: String
) {
	fun parseTsv(): List<ResourceLine> {
		return parseDelimited("\t")
	}

	fun parseDelimited(regex: String): List<ResourceLine> {
		return data.split("\n").map { ResourceLine(it, regex) }
	}


}