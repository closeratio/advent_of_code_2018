package com.closeratio.aoc2018.common.math

class Vec2d private constructor(x: Double, y: Double) : Vec2<Double>(x, y) {

    companion object {
        val ZERO = from(0, 0)
        fun from(x: Number, y: Number) = Vec2d(x.toDouble(), y.toDouble())
    }

    fun toVec2d() = Vec2i.from(x, y)

    operator fun times(i: Number) = Vec2d(x * i.toDouble(), y * i.toDouble())
    operator fun plus(other: Vec2d) = Vec2d(x + other.x, y + other.y)
    operator fun minus(other: Vec2d) = this + (other * -1)

}