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

	fun orderValue(mapWidth: Int) = position.y * mapWidth + position.x

	fun iterate(entities: List<Entity>, mapDimensions: Vec2i) {
		val enemyClass = enemyClass()

		val enemies = entities.filterIsInstance(enemyClass)
		if (enemies.isEmpty()) {
			return
		}

		val enemiesInOrder = enemies
				.sortedBy { it.orderValue(mapDimensions.x) }
				.sortedBy { it.currentHealth }
				.sortedBy { position.manhattan(it.position) }

		val targetEnemy = enemiesInOrder.first()

		if (position.manhattan(targetEnemy.position) > 1) {
			moveToTarget(targetEnemy, entities, mapDimensions)
		}

		if (position.manhattan(targetEnemy.position) == 1) {
			attackTarget(targetEnemy)
		}
	}

	private fun moveToTarget(target: CombatEntity, entities: List<Entity>, mapDimensions: Vec2i) {
		val entityPositions = entities
				.map { it.position }
				.toSet()

		val openSet = PriorityQueue(comparingInt<Vec2i> { it.y * mapDimensions.x + it.x }
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

			if (currentPosition.manhattan(target.position) == 1) {
				val path = ArrayList<Vec2i>()
				var current = currentPosition

				while (current != position) {
					path.add(0, current)
					current = previousMap[current]!!
				}

				position = path[0]

				return
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

	}

	private fun attackTarget(target: CombatEntity) {
		target.currentHealth -= attackPower
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(position=$position, currentHealth=$currentHealth)"
	}

}