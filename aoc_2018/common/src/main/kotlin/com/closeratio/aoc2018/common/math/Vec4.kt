package com.closeratio.aoc2018.common.math

import java.lang.Math.sqrt

abstract class Vec4<T: Number>(
		val w: T,
		val x: T,
		val y: T,
		val z: T
) {

	fun length() = sqrt(length2())
	fun length2() = w.toDouble() * w.toDouble() + x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble() + z.toDouble() * z.toDouble()

	override fun toString(): String {
		return "${javaClass.simpleName}(w=$w, x=$x, y=$y, z=$z)"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Vec4<*>

		if (w != other.w) return false
		if (x != other.x) return false
		if (y != other.y) return false
		if (z != other.z) return false

		return true
	}

	override fun hashCode(): Int {
		var result = w.hashCode()
		result = 31 * result + x.hashCode()
		result = 31 * result + y.hashCode()
		result = 31 * result + z.hashCode()
		return result
	}


}

