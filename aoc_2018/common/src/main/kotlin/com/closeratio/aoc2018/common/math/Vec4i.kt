package com.closeratio.aoc2018.common.math

import java.lang.Math.abs

class Vec4i private constructor(w: Int, x: Int, y: Int, z: Int): Vec4<Int>(w, x, y, z) {

	fun manhattan(other: Vec4i) = abs(w - other.w) + abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

	companion object {
		val ZERO = from(0, 0, 0, 0)
		fun from(w: Number, x: Number, y: Number, z: Number) = Vec4i(w.toInt(), x.toInt(), y.toInt(), z.toInt())
	}

	operator fun plus(v: Vec4i): Vec4i {
		return Vec4i(w + v.w, x + v.x, y + v.y, z + v.z)
	}

}