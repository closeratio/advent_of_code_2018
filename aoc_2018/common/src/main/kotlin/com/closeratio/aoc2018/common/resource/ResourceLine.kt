package com.closeratio.aoc2018.common.resource

class ResourceLine(
		line: String,
		delimiter: String
) {
	val elements = line.split(delimiter)
}
