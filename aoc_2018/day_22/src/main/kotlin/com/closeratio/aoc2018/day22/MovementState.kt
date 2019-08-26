package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i

data class MovementState(
        val region: Region,
        val equipment: EquipmentType
) {
    fun computeConnectedNodes(
            limit: Vec2i,
            getRegionCallback: (Vec2i) -> Region
    ): Set<MovementState> {
        val regionChanges = listOf(
                region.position.up(),
                region.position.right(),
                region.position.down(),
                region.position.left()
        )
                .asSequence()
                .filter { it.x >= 0 && it.y >= 0 }
                .filter { it.x <= limit.x && it.y <= limit.y }
                .map { getRegionCallback(it) }
                .filter { region -> equipment.canBeUsedIn(region.type) }
                .map { MovementState(it, equipment) }
                .toList()

        val equipmentChanges = EquipmentType
                .values()
                .filter { it != equipment }
                .filter { it.canBeUsedIn(region.type) }
                .map { MovementState(region, it) }

        return (regionChanges + equipmentChanges).toSet()
    }
}