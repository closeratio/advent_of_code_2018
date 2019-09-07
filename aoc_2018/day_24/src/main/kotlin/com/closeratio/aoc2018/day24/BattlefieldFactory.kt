package com.closeratio.aoc2018.day24

import com.closeratio.aoc2018.day24.Allegiance.IMMUNE_SYSTEM
import com.closeratio.aoc2018.day24.Allegiance.INFECTION

object BattlefieldFactory {

    fun from(lines: List<String>): Battlefield {
        lateinit var currAllegiance: Allegiance
        val groups = arrayListOf<Group>()
        var groupIndex = 1

        lines.filter { it.isNotBlank() }
                .forEach { line ->
                    when (line.toLowerCase()) {
                        "immune system:" -> {
                            currAllegiance = IMMUNE_SYSTEM
                            groupIndex = 1
                        }
                        "infection:" -> {
                            currAllegiance = INFECTION
                            groupIndex = 1
                        }
                        else -> {
                            groups.add(parseGroup(line, groupIndex, currAllegiance))
                            groupIndex++
                        }
                    }
                }

        return Battlefield(groups)
    }

    private val lineRegex = """(\d+) units each with (\d+) hit points (\([a-z;, ]+\) )?with an attack that does (\d+) (\w+) damage at initiative (\d+)""".toRegex()

    private fun parseGroup(
            line: String,
            index: Int,
            allegiance: Allegiance
    ): Group {
        val groups = lineRegex.matchEntire(line)!!
                .groupValues
                .drop(1)

        return Group(
                index,
                allegiance,
                groups[0].toInt(),
                groups[1].toInt(),
                parseImmunities(groups[2].trim(' ', '(', ')')),
                parseWeaknesses(groups[2].trim(' ', '(', ')')),
                groups[3].toInt(),
                DamageType(groups[4].toUpperCase()),
                groups[5].toInt()
        )
    }

    private val weaknessRegex = """weak to ([a-z ,]+)""".toRegex()

    private fun parseWeaknesses(line: String): Set<DamageType> = weaknessRegex
            .find(line)
            ?.groupValues
            ?.get(1)
            ?.split(", ")
            ?.map { DamageType(it.toUpperCase()) }
            ?.toSet()
            ?: setOf()

    private val immuneRegex = """immune to ([a-z ,]+)""".toRegex()

    private fun parseImmunities(line: String): Set<DamageType> = immuneRegex
            .find(line)
            ?.groupValues
            ?.get(1)
            ?.split(", ")
            ?.map { DamageType(it.toUpperCase()) }
            ?.toSet()
            ?: setOf()

}