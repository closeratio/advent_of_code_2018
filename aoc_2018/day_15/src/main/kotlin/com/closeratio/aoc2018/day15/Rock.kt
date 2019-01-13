package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day15.ConsoleColours.RESET
import com.closeratio.aoc2018.day15.ConsoleColours.WHITE
import java.util.*

class Rock(
		id: UUID,
		position: Vec2i
): Entity(id, position) {

	override fun serialised(): String {
		return "${WHITE.code}#${RESET.code}"
	}
}