package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

class Goblin(
		id: UUID,
		position: Vec2i,
		startingHealth: Int,
		attackPower: Int)
	: Entity(id, position, startingHealth, attackPower)