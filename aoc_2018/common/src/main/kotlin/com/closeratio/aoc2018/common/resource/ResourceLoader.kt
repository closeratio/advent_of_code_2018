package com.closeratio.aoc2018.common.resource

object ResourceLoader {

	fun loadResource(path: String): Resource {
		return Resource(javaClass.getResource(path).readText().trim())
	}

}