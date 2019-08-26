package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i

class Region(
        val position: Vec2i,
        val type: RegionType,
        val geologicIndex: Int,
        val erosionLevel: Int
) {

    override fun toString(): String {
        return "Region(position=$position, type=$type)"
    }

}