package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.math.Vec2i.Companion.ZERO
import com.closeratio.aoc2018.day22.EquipmentType.TORCH

class RegionMap(
        val depth: Int,
        val target: Vec2i
) {

    val regionMap = hashMapOf<Vec2i, Region>()

    init {
        // Populate source and target
        (0..target.y).forEach { y ->
            (0..target.x).forEach { x ->
                getOrCreateRegion(Vec2i.from(x, y))
            }
        }
    }

    private fun getOrCreateRegion(pos: Vec2i) = regionMap.getOrPut(
            pos,
            { Region(pos, getRegionType(pos), getErosionLevel(pos)) }
    )

    private fun getGeologicIndex(pos: Vec2i): Int {
        return when {
            pos == ZERO -> 0
            pos == target -> 0
            pos.y == 0 -> pos.x * 16807
            pos.x == 0 -> pos.y * 48271
            else -> getOrCreateRegion(pos.left()).erosionLevel * getOrCreateRegion(pos.up()).erosionLevel
        }
    }

    private fun getErosionLevel(pos: Vec2i): Int = (getGeologicIndex(pos) + depth) % 20183

    private fun getRegionType(pos: Vec2i): RegionType = when (getErosionLevel(pos) % 3) {
        0 -> RegionType.ROCKY
        1 -> RegionType.WET
        2 -> RegionType.NARROW
        else -> throw IllegalStateException("Erosion level modulo 3 is not between 0 and 2")
    }

    fun totalRiskLevel(): Int {
        return (0..target.y)
                .flatMap { y ->
                    (0..target.x).map { x ->
                        val pos = Vec2i.from(x, y)
                        getOrCreateRegion(pos).type.riskLevel
                    }
                }.sum()
    }

    fun timeToTarget(): Int {
        val startState = MovementState(regionMap.getValue(ZERO), TORCH)
        val targetState = MovementState(regionMap.getValue(target), TORCH)
        val distanceMap = hashMapOf(
                startState to 0
        )

        val unprocessedNodes = hashSetOf(startState)
        val processedNodes = hashSetOf<MovementState>()

        while (unprocessedNodes.isNotEmpty()) {
            // Get next nearest room
            val curr = distanceMap
                    .entries
                    .minBy { it.value + it.key.region.position.manhattan(targetState.region.position) }!!
                    .key

            // Get distance of current room
            val currValue = distanceMap.getValue(curr)
            if (curr == targetState) {
                return currValue
            }

            // Remove current room from unprocessed list, save its distance to the processed nodes map, and remove
            // it from the distance map so that it's doesn't get considered in the next iteration.
            unprocessedNodes.remove(curr)
            processedNodes.add(curr)
            distanceMap.remove(curr)

            curr.computeConnectedNodes(target.plus(target)) { getOrCreateRegion(it) }
                    .filter { it !in processedNodes }
                    .forEach { state ->
                        val nextDist = currValue + when {
                            (state.region != curr.region) && (state.equipment == curr.equipment) -> 1
                            (state.region == curr.region) && (state.equipment != curr.equipment) -> 7
                            else -> throw IllegalStateException("Invalid state change from $curr to $state")
                        }

                        if (state !in distanceMap) {
                            distanceMap[state] = nextDist
                            unprocessedNodes.add(state)
                        } else if (nextDist < distanceMap.getValue(state)) {
                            distanceMap[state] = nextDist
                        }
                    }
        }

        throw IllegalStateException("Value should have been computed before reaching this point")
    }

}