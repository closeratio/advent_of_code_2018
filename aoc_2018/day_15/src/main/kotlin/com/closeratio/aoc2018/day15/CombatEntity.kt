package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*
import java.util.Comparator.comparingInt

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

			// Compute required path for movement into each attack position
			val attackPaths = stepsToEnemies(entities, mapDimensions)

			// If no routes could be computed, it's not possible to move into any attack positions, so end turn
			val attackPath = attackPaths
					.sortedBy { it.last().orderValue(mapDimensions) }
					.sortedBy { it.size }
					.firstOrNull() ?: return

			position = attackPath[1]
		}

		if (inAttackPosition(attackPositions)) {
			attackTarget(enemies, mapDimensions)
		}
	}

	private fun inAttackPosition(attackPositions: List<Vec2i>) = attackPositions.any { position == it }

	private fun stepsToEnemies(entities: List<Entity>, mapDimensions: Vec2i): List<List<Vec2i>> {
		val entityPositions = entities.map { it.position }.toSet()

		val openSet = PriorityQueue(comparingInt<Vec2i> { position.manhattan(it) }
				.then(comparingInt { it.orderValue(mapDimensions) }))
				.apply {
					addAll(position
							.adjacent()
							.filter { it -> it !in entityPositions })
				}
		val closedSet = HashSet<Vec2i>()

		val previousMap = HashMap<Vec2i, Vec2i>(openSet.associate { Pair(it, position) })
		val distanceMap = HashMap<Vec2i, Int>(openSet.associate { Pair(it, 1) })

		while (openSet.isNotEmpty()) {
			val currentPosition = openSet.remove()
			val currentDistance = distanceMap[currentPosition]!!
			closedSet.add(currentPosition)

			openSet.addAll(currentPosition
					.adjacent()
					.filter { it !in entityPositions }
					.map {
						val tentativeDistance = currentDistance + 1

						if (it !in distanceMap || distanceMap[it]!! > tentativeDistance) {
							distanceMap[it] = tentativeDistance
							previousMap[it] = currentPosition
						} else if (distanceMap[it]!! == tentativeDistance
								&& currentPosition.orderValue(mapDimensions) < previousMap[it]!!.orderValue(mapDimensions)) {
							previousMap[it] = currentPosition
						}

						it
					}
					.filter { it !in closedSet }
					.filter { it !in openSet })

		}

		return entities
				.filterIsInstance(enemyClass())
				.filter { entity -> entity.position.adjacent().any { it !in entityPositions } }
				.filter { entity -> entity.position.adjacent().any { it in previousMap } }
				.flatMap { entity ->
					entity.position.adjacent()
							.filter { it !in entityPositions }
							.filter { it in previousMap }
							.map { buildPath(it, previousMap) }
				}
	}

	private fun buildPath(end: Vec2i, previousMap: Map<Vec2i, Vec2i>): List<Vec2i> {
		val path = arrayListOf<Vec2i>()
		var curr: Vec2i? = end
		while (curr != null) {
			path.add(0, curr)
			curr = previousMap[curr]
		}

		return path
	}

	private fun attackTarget(enemies: List<CombatEntity>, mapDimensions: Vec2i) {
		enemies.filter { position.manhattan(it.position) == 1 }
				.sortedBy { it.orderValue(mapDimensions) }
				.sortedBy { it.currentHealth }
				.first().currentHealth -= attackPower
	}

	fun isAlive() = currentHealth > 0

	fun targetsAvailable(entities: List<Entity>): Boolean {
		return entities.any { it.javaClass == enemyClass() }
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(position=$position, currentHealth=$currentHealth)"
	}

}