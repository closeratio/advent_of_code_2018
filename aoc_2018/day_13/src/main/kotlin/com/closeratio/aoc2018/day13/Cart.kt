package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day13.Orientation.*

class Cart(
		val id: Int,
		var position: Vec2i,
		var orientation: Orientation
) {
	var nextTurnDirection = TurnDirection.LEFT

	fun move(trackMap: Map<Vec2i, Track>) {
		position = when (orientation) {
			UP -> position.up()
			DOWN -> position.down()
			LEFT -> position.left()
			RIGHT -> position.right()
		}

		val newPos = trackMap.getValue(position)

		when (newPos.trackType) {
			TrackType.CROSSROADS -> {
				updateOrientationOnCrossroads()
				iterateTurnDirection()
			}
			TrackType.BACKWARD_SLASH -> {
				orientation =  when (orientation) {
					UP -> LEFT
					LEFT -> UP
					DOWN -> RIGHT
					RIGHT -> DOWN
				}
			}
			TrackType.FORWARD_SLASH -> {
				orientation =  when (orientation) {
					UP -> RIGHT
					RIGHT -> UP
					DOWN -> LEFT
					LEFT -> DOWN
				}
			}
			else -> {}
		}
	}

	private fun updateOrientationOnCrossroads() {
		orientation = when(nextTurnDirection) {
			TurnDirection.LEFT -> {
				when (orientation) {
					UP -> LEFT
					LEFT -> DOWN
					DOWN -> RIGHT
					RIGHT -> UP
				}
			}
			TurnDirection.STRAIGHT -> orientation
			TurnDirection.RIGHT -> {
				when (orientation) {
					UP -> RIGHT
					RIGHT -> DOWN
					DOWN -> LEFT
					LEFT -> UP
				}
			}
		}
	}

	private fun iterateTurnDirection() {
		nextTurnDirection = when(nextTurnDirection) {
			TurnDirection.LEFT -> TurnDirection.STRAIGHT
			TurnDirection.STRAIGHT -> TurnDirection.RIGHT
			TurnDirection.RIGHT -> TurnDirection.LEFT
		}
	}

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