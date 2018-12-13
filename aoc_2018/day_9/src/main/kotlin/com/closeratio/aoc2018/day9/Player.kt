package com.closeratio.aoc2018.day9

import java.util.*

class Player(
		val id: Int,
		val marbles: ArrayList<Marble> = arrayListOf()
) {


	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Player

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id.hashCode()
	}

	override fun toString(): String {
		return "Player(id=$id)"
	}

}