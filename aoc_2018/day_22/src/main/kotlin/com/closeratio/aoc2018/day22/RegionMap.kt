package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.common.math.Vec2i.Companion.ZERO

class RegionMap(
        val depth: Int,
        val target: Vec2i
) {

    val regionMap = hashMapOf<Vec2i, Region>()

    init {
        // Populate source and target
        (0..target.y).forEach { y ->
            (0..target.x).forEach { x ->
                val pos = Vec2i.from(x, y)
                regionMap[pos] = Region(pos, getRegionType(pos), getGeologicIndex(pos), getErosionLevel(pos))
            }
        }
    }

    private fun getGeologicIndex(pos: Vec2i): Int {
        return when {
            pos == ZERO -> 0
            pos == target -> 0
            pos.y == 0 -> pos.x * 16807
            pos.x == 0 -> pos.y * 48271
            else -> regionMap.getValue(pos.left()).erosionLevel * regionMap.getValue(pos.up()).erosionLevel
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
                        regionMap.getValue(pos).type.riskLevel
                    }
                }.sum()
    }

}