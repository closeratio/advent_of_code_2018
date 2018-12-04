package com.closeratio.aoc2018.common.math

import java.lang.Math.abs
import java.lang.Math.sqrt

abstract class Vec2<T: Number>(
		val x: T,
		val y: T
) {

	fun length() = sqrt(length2())
	fun length2() = x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble()

	fun manhattan() = abs(x.toDouble()) + abs(y.toDouble())

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Vec2<*>

		if (x != other.x) return false
		if (y != other.y) return false

		return true
	}

	override fun hashCode(): Int {
		var result = x.hashCode()
		result = 31 * result + y.hashCode()
		return result
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(x=$x, y=$y)"
	}


}

