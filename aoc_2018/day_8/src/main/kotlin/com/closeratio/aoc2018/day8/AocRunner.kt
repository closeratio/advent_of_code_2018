package com.closeratio.aoc2018.day8

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val license = License.from(loadResource("/input.txt").data)

		println(license.metadataSum())
		println(license.computeValue())
	}

}