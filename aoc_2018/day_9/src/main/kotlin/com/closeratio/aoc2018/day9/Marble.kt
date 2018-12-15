package com.closeratio.aoc2018.day9

class Marble(
		val value: Long) {

	lateinit var next: Marble
	lateinit var previous: Marble

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Marble

		if (value != other.value) return false

		return true
	}

	override fun hashCode(): Int {
		return value.hashCode()
	}

	override fun toString(): String {
		return "Marble(value=$value)"
	}

}
