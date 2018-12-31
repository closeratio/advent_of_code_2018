package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

abstract class Entity(
		val id: UUID,
		var position: Vec2i,
		val startingHealth: Int,
		val attackPower: Int) {

}

