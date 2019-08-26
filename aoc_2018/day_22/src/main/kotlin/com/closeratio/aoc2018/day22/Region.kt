package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.common.math.Vec2i

class Region(
        val position: Vec2i,
        val type: RegionType,
        val erosionLevel: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Region

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "Region(position=$position, type=$type)"
    }

}