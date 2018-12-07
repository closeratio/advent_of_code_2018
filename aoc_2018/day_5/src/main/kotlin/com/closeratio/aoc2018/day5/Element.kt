package com.closeratio.aoc2018.day5

import java.util.*
import java.util.UUID.randomUUID

class Element(
		val value: Char
) {

	val id: UUID = randomUUID()

	fun reactsWith(other: Element) = value != other.value && value.toUpperCase() == other.value.toUpperCase()

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Element

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id.hashCode()
	}

	override fun toString(): String {
		return "Element(value=$value)"
	}

}
