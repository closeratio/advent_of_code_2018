package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day15.ConsoleColours.GREEN
import com.closeratio.aoc2018.day15.ConsoleColours.RESET
import java.util.*

class Elf(
		id: UUID,
		position: Vec2i,
		startingHealth: Int,
		attackPower: Int)
	: CombatEntity(id, position, startingHealth, attackPower) {

	override fun enemyClass() = Goblin::class.java

	override fun serialised(): String {
		return "${GREEN.code}E${RESET.code}"
	}

}