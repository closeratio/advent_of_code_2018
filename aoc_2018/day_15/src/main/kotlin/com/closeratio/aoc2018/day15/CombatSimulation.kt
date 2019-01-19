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

	// Return value indicates whether this was a "full round" of combat or not
	fun iterate(): Boolean {
		entities.filterIsInstance<CombatEntity>()
				.sortedBy { it.orderValue(mapDimensions) }
				.forEach { entity ->
					if (entity.isAlive()) {
						if (!entity.targetsAvailable(entities)) {
							return false
						}

						entity.iterate(entities, mapDimensions)
					}

					// Remove dead entities
					entities.removeAll(entities
							.filterIsInstance<CombatEntity>()
							.filter { it.currentHealth <= 0 })
				}

		return true
	}

	fun computeOutcome(iterOffset: Int = 0): Int {
		var iterations = 0
		while (entities.filterIsInstance<Elf>().isNotEmpty() && entities.filterIsInstance<Goblin>().isNotEmpty()) {
			if (iterate()) {
				iterations++
			}
		}

		return (iterations + iterOffset) * entities
				.filterIsInstance<CombatEntity>()
				.map { it.currentHealth }
				.sum()
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
					}
					.flatten())
		}
	}
}