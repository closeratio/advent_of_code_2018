package com.closeratio.aoc2018.day5

import com.closeratio.aoc2018.common.resource.ResourceLoader

object AocRunner {

	@JvmStatic
	fun main(args: Array<String>) {
		val data = ResourceLoader.loadResource("/input.txt").data

		val elementChain = ElementChain.from(data)
		val reactedChain = elementChain.react()

		println(reactedChain.elements.size)

		val uniqueChars = data.toCharArray().map { it.toUpperCase() }.toSet()
		val smallest = uniqueChars.map {
			val proposedString = data.replace(it.toString(), "").replace(it.toLowerCase().toString(), "")
			val chain = ElementChain.from(proposedString)
			val reacted = chain.react()

			reacted.elements.size
		}.min()

		println(smallest)

	}

}