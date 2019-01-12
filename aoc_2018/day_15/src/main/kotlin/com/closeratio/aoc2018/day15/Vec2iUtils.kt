package com.closeratio.aoc2018.day15

import com.closeratio.aoc2018.common.math.Vec2i

fun Vec2i.adjacent() = listOf(up(), left(), right(), down())