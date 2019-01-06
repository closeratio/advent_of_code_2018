package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

class CombatSimulation private constructor(
		val entities: List<Entity>
) {

	val positionMap = entities.associateBy { it.position }

	fun iterate() {

	}

	companion object {
		fun from(data: String): CombatSimulation {
			return CombatSimulation(data.split("\n")
					.map { it.trim() }
					.mapIndexed { y, line ->
						line.mapIndexedNotNull { x, c ->
							val id = UUID.randomUUID()
							val pos = Vec2i.from(x, y)
							when (c) {
								'#' -> Rock(id, pos)
								'E' -> Elf(id, pos, 200, 3)
								'G' -> Goblin(id, pos, 200, 3)
								'.' -> null
								else -> throw IllegalArgumentException("Unknown char: \"$c\"")
							}
						}
					}.flatten())
		}
	}
}