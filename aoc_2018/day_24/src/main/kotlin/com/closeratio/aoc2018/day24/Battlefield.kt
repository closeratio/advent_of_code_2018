package com.closeratio.aoc2018.day24

import com.closeratio.aoc2018.day24.Allegiance.IMMUNE_SYSTEM

class Battlefield(
        val boost: Int,
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

    fun fight() {
        selectTargets()
                .sortedByDescending { it.first.initiative }
                .forEach { (attacker, defender) ->
                    attacker.attack(defender)
                }
    }

    private fun remainingAllegiances(): Set<Allegiance> = groups
            .filter { it.unitCount > 0 }
            .map { it.allegiance }
            .toSet()

    private fun remainingAllegianceCount(): Int = remainingAllegiances().size

    private fun isUnwinnable(): Boolean = selectTargets()
            .none { it.first.effectivePower() > it.second.hitPointsPerUnit }

    fun fightUntilFinished(): BattleResult? {
        var fightCount = 0
        while (remainingAllegianceCount() > 1) {
			if (isUnwinnable()) {
				println("Unwinnable battlefield detected with $boost")
				return null
			}

            fight()
            fightCount++
        }

        val unitCount = groups
                .filter { it.unitCount > 0 }
                .map { it.unitCount }
                .sum()

        return BattleResult(
                remainingAllegiances().first(),
                fightCount,
                unitCount
        )
    }

    fun plusBoost(boost: Int): Battlefield = Battlefield(
            boost,
            groups.map {
                if (it.allegiance == IMMUNE_SYSTEM) {
                    it.plusBoost(boost)
                } else {
                    it.plusBoost(0)
                }
            }
    )

}

