package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i
import java.util.*

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

		val openSet = (1..mapDimensions.y)
				.flatMap { y ->
					(1..mapDimensions.x).map { x ->
						Vec2i.from(x, y)
					}
				}
				.filter { it !in entityPositions }
				.sortedBy { position.manhattan(it) }





	}

	private fun attackTarget(target: CombatEntity) {
		target.currentHealth -= attackPower
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(position=$position, currentHealth=$currentHealth)"


	}


}