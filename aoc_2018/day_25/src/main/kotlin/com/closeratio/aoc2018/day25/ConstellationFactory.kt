package com.closeratio.aoc2018.day25

import com.closeratio.aoc2018.common.math.Vec4i

object ConstellationFactory {

    fun fromLines(lines: List<String>) = from(lines.map {
        val elements = it.split(",").map { it.trim().toInt() }
        Vec4i.from(
                elements[0], elements[1], elements[2], elements[3]
        )
    })

    fun from(vecs: List<Vec4i>): Set<Constellation> = setOf()

}