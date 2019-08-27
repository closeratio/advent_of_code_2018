package com.closeratio.aoc2018.common.math

import java.lang.Math.abs

class Vec3i private constructor(x: Int, y: Int, z: Int): Vec3<Int>(x, y, z) {

	fun manhattan(other: Vec3i) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)
	fun manhattan(otherX: Int, otherY: Int, otherZ: Int) = abs(x - otherX) + abs(y - otherY) + abs(z - otherZ)

	companion object {
		val ZERO = from(0, 0, 0)
		fun from(x: Number, y: Number, z: Number) = Vec3i(x.toInt(), y.toInt(), z.toInt())
	}

	operator fun plus(v: Vec3i): Vec3i {
		return Vec3i(x + v.x, y + v.y, z + v.z)
	}

}