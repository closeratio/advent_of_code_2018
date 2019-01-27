package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

abstract class CombatEntity(
		id: UUID,
		position: Vec2i,
		startingHealth: Int,
		private val attackPower: Int)
	: Entity(id, position) {

	var currentHealth = startingHealth

	abstract fun enemyClass(): Class<out CombatEntity>

	fun orderValue(mapDimensions: Vec2i) = position.y * mapDimensions.x + position.x

	fun iterate(entities: List<Entity>, mapDimensions: Vec2i) {

		// Find enemies
		val enemies = entities.filterIsInstance(enemyClass())

		// If there are no enemies, end the turn
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
				.filter { it !in entityPositions || it == position }

		// If there are no attack positions to try to move to, end the turn
		if (attackPositions.isEmpty()) {
			return
		}

		// If not already in an attack position
		if (!inAttackPosition(attackPositions)) {

			// Select nearest attack position
			val distanceMap = generateDistanceMap(position, entities)
			val validAttackPositions = attackPositions.filter { it in distanceMap }
			val selectedAttackPosition = validAttackPositions
					.sortedBy { it.orderValue(mapDimensions) }
					.sortedBy { distanceMap[it]!! }
					.firstOrNull() ?: return

			// Find "best" position to move to
			val selectedPosition = generateDistanceMap(selectedAttackPosition, entities)
					.toList()
					.filter { it.first in position.adjacent() }
					.sortedBy { it.first.orderValue(mapDimensions) }
					.sortedBy { it.second }
					.first()
					.first

			position = selectedPosition
		}

		if (inAttackPosition(attackPositions)) {
			attackTarget(enemies, mapDimensions)
		}
	}

	private fun inAttackPosition(attackPositions: List<Vec2i>) = attackPositions.any { position == it }

	private fun attackTarget(enemies: List<CombatEntity>, mapDimensions: Vec2i) {
		enemies.filter { position.manhattan(it.position) == 1 }
				.sortedBy { it.orderValue(mapDimensions) }
				.sortedBy { it.currentHealth }
				.first().currentHealth -= attackPower
	}

	private fun generateDistanceMap(position: Vec2i, entities: List<Entity>): Map<Vec2i, Int> {
		val entityPositions = entities.map { it.position }.toSet()

		val distanceMap: HashMap<Vec2i, Int> = hashMapOf(position to 0)
		distanceMap.putAll(position
				.adjacent()
				.filter { it !in entityPositions }
				.map { Pair(it, 1) })

		val closedSet = hashSetOf(position)
		val openSet = linkedSetOf(*position
				.adjacent()
				.filter { it !in entityPositions }
				.toTypedArray())

		while (!openSet.isEmpty()) {
			val current = openSet.first()
			openSet.remove(current)
			closedSet.add(current)
			val adjDistance = distanceMap[current]!! + 1

			openSet.addAll(current
					.adjacent()
					.filter { it !in closedSet }
					.filter { it !in entityPositions }
					.filter { it !in openSet }
					.map {
						distanceMap[it] = adjDistance
						it
					})
		}

		return distanceMap
	}

	fun isAlive() = currentHealth > 0

	fun targetsAvailable(entities: List<Entity>): Boolean {
		return entities.any { it.javaClass == enemyClass() }
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(position=$position, currentHealth=$currentHealth)"
	}

}