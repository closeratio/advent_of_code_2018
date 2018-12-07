package com.closeratio.aoc2018.day5

import java.util.*
import kotlin.collections.LinkedHashSet

class ElementChain(
		val elements: List<Element>
) {

	fun react(): ElementChain {
		val chain = LinkedHashSet(elements)
		var removed = true

		while (removed) {
			removed = false

			val proposals = LinkedList(chain
					.windowed(2)
					.mapNotNull {
						if (it[0].reactsWith(it[1])) {
							ProposedElementRemoval(it[0], it[1])
						} else {
							null
						}
					})

			while (proposals.isNotEmpty()) {
				val proposal = proposals.pop()
				chain.remove(proposal.firstElement)
				chain.remove(proposal.secondElement)
				removed = true

				if (proposals.isNotEmpty() && proposal.secondElement == proposals.peek().firstElement) {
					proposals.pop()
				}
			}
		}

		return ElementChain(ArrayList(chain))
	}

	companion object {
		fun from(line: String): ElementChain {
			return ElementChain(line.toCharArray().map { Element(it) })
		}
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as ElementChain

		if (elements != other.elements) return false

		return true
	}

	override fun hashCode(): Int {
		return elements.hashCode()
	}


}