package com.closeratio.aoc2018.day24

class Group(
        val groupIndex: Int,
        val allegiance: Allegiance,
        val initialUnitCount: Int,
        val hitPointsPerUnit: Int,
        val immunities: Set<DamageType>,
        val weaknesses: Set<DamageType>,
        val attackDamage: Int,
        val attackDamageType: DamageType,
        val initiative: Int
) {

    var unitCount: Int = initialUnitCount

    fun effectivePower(): Int = attackDamage * unitCount

    fun selectTarget(availableGroups: Set<Group>): Group? {
        val candidateGroups = availableGroups
                .filter { it != this }
                .filter { it.allegiance != allegiance }
                .filter { it.unitCount > 0 }
                .filter { computeDamageAmount(it) > 0 }

        if (candidateGroups.isEmpty()) {
            return null
        }

        return candidateGroups
                .sortedByDescending { it.initiative }
                .sortedByDescending { it.effectivePower() }
                .maxBy { computeDamageAmount(it) }!!
    }

    private fun computeDamageAmount(target: Group): Int {
        val baseDamage = effectivePower()

        return when {
            target.immunities.contains(attackDamageType) -> 0
            target.weaknesses.contains(attackDamageType) -> baseDamage * 2
            else -> baseDamage
        }
    }

    fun applyDamage(amount: Int) {
        unitCount = (unitCount - (amount / hitPointsPerUnit)).coerceAtLeast(0)
    }

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

    override fun toString(): String {
        return "Group(groupIndex=$groupIndex, allegiance=$allegiance)"
    }

}