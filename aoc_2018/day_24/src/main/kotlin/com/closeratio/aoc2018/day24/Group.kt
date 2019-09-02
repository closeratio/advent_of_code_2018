package com.closeratio.aoc2018.day24

class Group(
        val groupIndex: Int,
        val allegiance: Allegiance,
        val initialUnitCount: Int,
        val initialHitPointsPerUnit: Int,
        val weaknesses: Set<DamageType>,
        val attackPower: Int,
        val attackDamageType: DamageType,
        val initiative: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Group

        if (groupIndex != other.groupIndex) return false
        if (allegiance != other.allegiance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = groupIndex
        result = 31 * result + allegiance.hashCode()
        return result
    }



}