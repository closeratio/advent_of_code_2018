package com.closeratio.aoc2018.day19

import com.closeratio.aoc2018.common.math.Vec2i

class NodeGraph {

    val nodes: HashMap<Vec2i, Node> = hashMapOf()

    fun addNodeIfNecessary(pos: Vec2i): Node = nodes.getOrPut(pos) { Node(pos) }

    fun printGraph(): String {
        val minX = nodes.keys.map { it.x }.min()!!
        val maxX = nodes.keys.map { it.x }.max()!!

        val minY = nodes.keys.map { it.y }.min()!!
        val maxY = nodes.keys.map { it.y }.max()!!

        return (minY..(maxY * 2))
                .map { y ->
                    (minX..(maxX * 2))
                            .map { x ->
                                val pos = Vec2i.from(x / 2, y / 2)
                                if (x == 0 && y == 0) {
                                    "X"
                                } else if (x % 2 == 0 && y % 2 == 0) {
                                    if (pos in nodes) {
                                        "."
                                    } else {
                                        " "
                                    }
                                } else if (x % 2 == 0) {
                                    val above = pos
                                    val below = pos.down()

                                    if (above in nodes && nodes.getValue(above).connectedNodes.contains(Node(below))) {
                                        "-"
                                    } else {
                                        "#"
                                    }
                                } else if (y % 2 == 0) {
                                    val left = pos
                                    val right = pos.right()

                                    if (left in nodes && nodes.getValue(left).connectedNodes.contains(Node(right))) {
                                        "|"
                                    } else {
                                        "#"
                                    }
                                } else {
                                    "#"
                                }
                            }
                            .joinToString("")
                }
                .joinToString("\n")
    }

}
