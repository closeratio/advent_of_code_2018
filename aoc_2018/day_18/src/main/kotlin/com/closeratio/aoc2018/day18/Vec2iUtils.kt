package com.closeratio.aoc2018.day18

import com.closeratio.aoc2018.common.math.Vec2i

fun Vec2i.surrounding(): Set<Vec2i> {
	return setOf(
			up(),
			up().right(),
			right(),
			right().down(),
			down(),
			down().left(),
			left(),
			left().up())
}