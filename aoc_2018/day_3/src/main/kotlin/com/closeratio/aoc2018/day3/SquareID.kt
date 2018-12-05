package com.closeratio.aoc2018.day3

class SquareID(val id: Int) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as SquareID

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id
	}

	override fun toString(): String {
		return "SquareID(id=$id)"
	}

}
