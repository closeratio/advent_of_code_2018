package com.closeratio.aoc2018.common

class ResourceLine(
		val line: String,
		val delimiter: String
) {
	val elements = line.split(delimiter)
}
