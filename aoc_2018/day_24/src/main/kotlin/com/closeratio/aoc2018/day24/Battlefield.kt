package com.closeratio.aoc2018.day24

class Battlefield(
        val groups: List<Group>
) {

    fun selectTargets(): List<Pair<Group, Group>> {
        val availableGroups = groups.filter { it.unitCount > 0 }.toMutableSet()

        return groups
                .sortedByDescending { it.initiative }
                .sortedByDescending { it.effectivePower() }
                .mapNotNull { group ->
                    val target = group.selectTarget(availableGroups)
                    if (target != null) {
                        availableGroups.remove(target)
                        group to target
                    } else {
                        null
                    }
                }
    }

}