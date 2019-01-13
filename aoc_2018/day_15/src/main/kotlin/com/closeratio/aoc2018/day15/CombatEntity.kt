package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*
import java.util.Comparator.comparingInt

abstract class CombatEntity(
		id: UUID,
		position: Vec2i,
		startingHealth: Int,
		val attackPower: Int)
	: Entity(id, position) {

	var currentHealth = startingHealth

	abstract fun enemyClass(): Class<out CombatEntity>

	fun orderValue(mapDimensions: Vec2i) = position.y * mapDimensions.x + position.x

	fun iterate(entities: List<Entity>, mapDimensions: Vec2i) {
		val enemyClass = enemyClass()

		// Find enemies
		val enemies = entities.filterIsInstance(enemyClass)
		if (enemies.isEmpty()) {
			return
		}

		// Compute positions of all entities
		val entityPositions = entities
				.map { it.position }
				.toSet()

		// Compute positions to move into for attack
		val attackPositions = enemies
				.flatMap { it.position.adjacent() }
				.toSet()
				.filter { it !in entityPositions }

		// If not already in an attack position
		if (!inAttackPosition(attackPositions)) {

			// Compute required path for movement into each attack position
			val attackPath = attackPositions
					.mapNotNull { stepsToPosition(it, entityPositions, mapDimensions) }
					.sortedBy { it[0].orderValue(mapDimensions) }
					.sortedBy { it.size }
					.first()

			position = attackPath[0]
		}

		if (inAttackPosition(attackPositions)) {
			attackTarget(enemies, mapDimensions)
		}
	}

	private fun inAttackPosition(attackPositions: List<Vec2i>) = attackPositions.map { position == it }.any()

	private fun stepsToPosition(target: Vec2i, entityPositions: Set<Vec2i>, mapDimensions: Vec2i): List<Vec2i>? {
		val openSet = PriorityQueue(comparingInt<Vec2i> { it.orderValue(mapDimensions) }
				.then(comparingInt { position.manhattan(it) }))
				.apply {
					addAll(position
							.adjacent()
							.filter { it -> it !in entityPositions })
				}
		val closedSet = HashSet<Vec2i>()

		val previousMap = HashMap<Vec2i, Vec2i>(openSet.associate { Pair(it, position) })

		while (openSet.isNotEmpty()) {
			val currentPosition = openSet.remove()
			closedSet.add(currentPosition)

			if (currentPosition == target) {
				val path = ArrayList<Vec2i>()
				var current = currentPosition

				while (current != position) {
					path.add(0, current)
					current = previousMap[current]!!
				}

				return path
			}

			openSet.addAll(currentPosition
					.adjacent()
					.filter { it !in entityPositions }
					.filter { it !in closedSet }
					.filter { it !in openSet }
					.map {
						previousMap[it] = currentPosition
						it
					})

		}

		// No path found
		return null

	}

	private fun attackTarget(enemies: List<CombatEntity>, mapDimensions: Vec2i) {
		enemies
				.sortedBy { it.orderValue(mapDimensions) }
				.sortedBy { it.currentHealth }
				.sortedBy { position.manhattan(it.position) == 1 }
				.first().currentHealth -= attackPower
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(position=$position, currentHealth=$currentHealth)"
	}

}