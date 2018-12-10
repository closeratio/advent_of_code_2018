package com.closeratio.aoc2018.day8

import java.util.*

class License(
		val root: LicenseNode
) {

	fun metadataSum(): Int {
		return root.metadataSum()
	}

	fun computeValue(): Int? {
		return root.computeValue()
	}

	companion object {
		fun from(data: String): License {
			val elementQueue = LinkedList(data.trim().split("""\s+""".toRegex()))

			return License(parseElement(elementQueue))
		}

		private fun parseElement(elementQueue: LinkedList<String>): LicenseNode {
			val childNodeCount = elementQueue.pop().toInt()
			val metadataCount = elementQueue.pop().toInt()

			return LicenseNode(
					UUID.randomUUID(),
					if (childNodeCount > 0) IntRange(1, childNodeCount).map { parseElement(elementQueue) } else listOf(),
					if (metadataCount > 0) IntRange(1, metadataCount).map { parseMetadata(elementQueue) } else listOf()
			)
		}

		private fun parseMetadata(elementQueue: LinkedList<String>): LicenseMetadata {
			return LicenseMetadata(elementQueue.pop().toInt())
		}
	}

	override fun toString(): String {
		return "License(root=$root)"
	}
}