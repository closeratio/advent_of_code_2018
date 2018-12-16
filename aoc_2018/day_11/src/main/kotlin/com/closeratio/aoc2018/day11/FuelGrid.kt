package com.closeratio.aoc2018.day11

import com.closeratio.aoc2018.common.geometry.BoundingBox
import com.closeratio.aoc2018.common.math.Vec2i
import java.util.concurrent.ConcurrentHashMap

class FuelGrid(
		val cells: Map<Vec2i, FuelCell>
) {

	private val powerCache = ConcurrentHashMap(cells.map { Pair(BoundingBox.from(it.key), it.value.powerLevel) }
			.associate { it })

	fun computeLargestTotalPowerCoord(lowerSize: Int, upperSize: Int): FuelCellConfiguration {

		val powerLevelList = IntRange(lowerSize, upperSize).flatMap { size ->
				IntRange(1, 300 - size + 1).flatMap { x ->
					IntRange(1, 300 - size + 1).map { y ->
						val config = FuelCellConfiguration(Vec2i.from(x, y), size)
						val box = BoundingBox.from(Vec2i.from(x, y), Vec2i.from(x + size - 1, y + size - 1))
						val powerLevel = getPower(box)
						Pair(config, powerLevel)
					}
				}
		}.sortedBy { it.second }

		return powerLevelList.last().first
	}

	fun getPower(box: BoundingBox): Int {
		val cached = powerCache[box]
		return if (cached != null) {
			cached
		} else {
			val computed = getBoundingBoxes(box).map { getPower(it) }.sum()
			if (box.area <= 64) {
				powerCache[box] = computed
			}
			computed
		}
	}

	fun getBoundingBoxes(box: BoundingBox): List<BoundingBox> {
		val width = box.width
		val height = box.height

		val midX = box.topLeft.x + box.width / 2 - 1
		val midY = box.topLeft.y + box.height / 2 - 1

		return when {
			box.area == 1 -> listOf(box)
			width == 1 -> {
				listOf(
						BoundingBox.from(
								box.topLeft,
								Vec2i.from(box.topLeft.x, midY)),
						BoundingBox.from(
								Vec2i.from(box.topLeft.x, midY + 1),
								box.bottomRight))
			}
			height == 1 -> {
				listOf(
						BoundingBox.from(
								box.topLeft,
								Vec2i.from(midX, box.topLeft.y)),
						BoundingBox.from(
								Vec2i.from(midX + 1, box.topLeft.y),
								box.bottomRight))
			}
			else -> {
				listOf(
						BoundingBox.from(
								box.topLeft,
								Vec2i.from(midX, midY)),
						BoundingBox.from(
								Vec2i.from(midX + 1, box.topLeft.y),
								Vec2i.from(box.bottomRight.x, midY)),
						BoundingBox.from(
								Vec2i.from(box.topLeft.x, midY + 1),
								Vec2i.from(midX, box.bottomRight.y)),
						BoundingBox.from(
								Vec2i.from(midX + 1, midY + 1),
								box.bottomRight))
			}
		}
	}

	companion object {
		fun from(serialNumber: Int): FuelGrid {
			return FuelGrid(
					IntRange(1, 300).flatMap { y ->
						IntRange(1, 300).map { x ->
							FuelCell.from(Vec2i.from(x, y), serialNumber)
						}
					}.associateBy { it.coordinate })
		}
	}
}