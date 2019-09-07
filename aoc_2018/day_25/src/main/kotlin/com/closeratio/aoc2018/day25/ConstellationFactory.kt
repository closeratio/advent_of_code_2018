package com.closeratio.aoc2018.day25

import com.closeratio.aoc2018.common.math.Vec4i

object ConstellationFactory {

    fun fromLines(lines: List<String>) = from(lines.map { line ->
        val elements = line.split(",").map { it.trim().toInt() }
        Vec4i.from(
                elements[0], elements[1], elements[2], elements[3]
        )
    })

    fun from(vecs: List<Vec4i>): Set<Constellation> {
        val constellations = mutableSetOf<Constellation>()

        vecs.forEach { vec ->
            val candidates = constellations.filter { it.isWithinConstellation(vec) }

            if (candidates.isNotEmpty()) {
                candidates.forEach { it.addPoint(vec) }

                if (candidates.size > 1) {
                    val merged = candidates.reduce { acc, curr -> acc + curr }
                    constellations.removeAll(candidates)
                    constellations.add(merged)
                }
            } else {
                constellations.add(Constellation(
                        mutableSetOf(vec)
                ))
            }
        }

        return constellations
    }

}