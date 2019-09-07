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

	fun fight() {
		selectTargets()
				.sortedByDescending { it.first.initiative }
				.forEach { (attacker, defender) ->
					attacker.attack(defender)
				}
	}

	fun remainingAllegianceCount(): Int = groups
			.filter { it.unitCount > 0 }
			.map { it.allegiance }
			.toSet()
			.size

	fun fightUntilFinished(): Int {
		var fightCount = 0
		while (remainingAllegianceCount() > 1) {
			fight()
			fightCount++
		}

		return groups
				.filter { it.unitCount > 0 }
				.map { it.unitCount }
				.sum()
	}

}