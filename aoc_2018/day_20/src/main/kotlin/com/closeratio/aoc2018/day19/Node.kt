package com.closeratio.aoc2018.day19

import com.closeratio.aoc2018.common.math.Vec2i

class Node(
        val position: Vec2i
) {

    val connectedNodes = hashSetOf<Node>()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "Node(${position.x}, ${position.y})"
    }

    fun addConnection(other: Node) {
        if (other !in connectedNodes) {
            connectedNodes.add(other)
        }
    }

}