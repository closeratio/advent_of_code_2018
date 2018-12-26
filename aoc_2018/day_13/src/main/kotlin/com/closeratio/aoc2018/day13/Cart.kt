package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day13.TurnDirection.LEFT

class Cart(
		val id: Int,
		var position: Vec2i,
		var orientation: Orientation
) {
	var nextTurnDirection = LEFT

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Cart

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id
	}

	override fun toString(): String {
		return "Cart(id=$id, position=$position, orientation=$orientation, nextTurnDirection=$nextTurnDirection)"
	}

}