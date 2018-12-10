package com.closeratio.aoc2018.day8

import java.util.*

class LicenseNode(
		val id: UUID = UUID.randomUUID(),
		val children: List<LicenseNode> = arrayListOf(),
		val metadata: List<LicenseMetadata> = arrayListOf()
) {


	override fun toString(): String {
		return "LicenseNode(id=$id, children=$children, metadata=$metadata)"
	}

	fun metadataSum(): Int {
		return children.map { it.metadataSum() }.sum() + metadata.map { it.value }.sum()
	}

	fun computeValue(): Int {
		return if (children.isEmpty()) {
			metadata.map { it.value }.sum()
		} else {
			metadata.map {
				val index = it.value
				if (index > 0 && index <= children.size) {
					children[index - 1].computeValue()
				} else {
					0
				}
			}.sum()
		}
	}
}
