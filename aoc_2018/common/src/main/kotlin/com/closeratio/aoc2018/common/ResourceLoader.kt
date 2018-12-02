package com.closeratio.aoc2018.common

object ResourceLoader {

	fun loadResource(path: String): Resource {
		return Resource(javaClass.getResource(path).readText().trim())
	}

}