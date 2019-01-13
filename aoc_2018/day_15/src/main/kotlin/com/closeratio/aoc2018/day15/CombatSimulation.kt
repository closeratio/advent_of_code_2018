package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

class CombatSimulation private constructor(
		entities: List<Entity>
) {

	val entities = ArrayList(entities)

	val initialPositionMap = entities.associateBy { it.position }
	val mapDimensions = Vec2i.from(
			(entities.map { it.position.x }.max()!! - entities.map { it.position.x }.min()!!) + 1,
			(entities.map { it.position.y }.max()!! - entities.map { it.position.y }.min() !!) + 1)

	fun iterate() {
		entities.filterIsInstance<CombatEntity>()
				.sortedBy { it.orderValue(mapDimensions) }
				.forEach { entity ->
					entity.iterate(entities, mapDimensions)

					// Remove dead entities
					entities.removeAll(entities
							.filterIsInstance<CombatEntity>()
							.filter { it.currentHealth <= 0 })
				}
	}

	fun serialisePositionMap(): String {
		val positionMap = entities.associateBy { it.position }

		return (0..(mapDimensions.y - 1)).map { y ->
			(0..(mapDimensions.x - 1)).map { x ->
				val entity = positionMap[Vec2i.from(x, y)]
				when (entity) {
					null -> " "
					else -> entity.serialised()
				}
			}.joinToString("")
		}.joinToString("\n")
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