package com.closeratio.aoc2018.day14

class Elf(
		val id: Int,
		var currentIndex: Int) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Elf

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id
	}

	override fun toString(): String {
		return "Elf(id=$id, currentIndex=$currentIndex)"
	}

}
