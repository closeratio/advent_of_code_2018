package com.closeratio.aoc2018.day24

import com.closeratio.aoc2018.day24.Allegiance.IMMUNE_SYSTEM
import com.closeratio.aoc2018.day24.Allegiance.INFECTION

object BattlefieldFactory {

    fun from(lines: List<String>): Battlefield {
        lateinit var currAllegiance: Allegiance
        val groups = arrayListOf<Group>()

        lines.filter { it.isNotBlank() }
                .forEach { line ->
                    when (line.toLowerCase()) {
                        "immune system:" -> currAllegiance = IMMUNE_SYSTEM
                        "infection:" -> currAllegiance = INFECTION
                        else -> groups.add(parseGroup(line, currAllegiance))
                    }
                }

        return Battlefield(groups)
    }

    private val lineRegex = "(\\d+) units each with (\\d+) hit points \\(([a-z;, ]+)\\) with an attack that does (\\d+) (\\w+) damage at initiative (\\d+)".toRegex()

    private fun parseGroup(line: String, allegiance: Allegiance): Group {
        val groups = lineRegex.matchEntire(line)!!.groupValues

        return Group(
                0,
                allegiance,
                0,
                0,
                setOf(),
                0,
                DamageType(""),
                0
        )
    }

}