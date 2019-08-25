package com.closeratio.aoc2018.day19

import com.closeratio.aoc2018.common.math.Vec2i

object GraphParser {

    fun parseGraph(input: String): NodeGraph {
        val graph = NodeGraph()
        val posStack = arrayListOf(Vec2i.ZERO)
        var currPos = Vec2i.ZERO

        input.trim('^', '$')
                .map { it.toString() }
                .forEach { element ->
                    when (element) {
                        "N", "E", "S", "W" -> {
                            val currNode = graph.addNodeIfNecessary(currPos)

                            val nextPos = when(element) {
                                "N" -> currPos.up()
                                "E" -> currPos.right()
                                "S" -> currPos.down()
                                "W" -> currPos.left()
                                else -> throw IllegalArgumentException("Unknown directional character: \"$element\"")
                            }
                            val nextNode = graph.addNodeIfNecessary(nextPos)

                            currNode.addConnection(nextNode)
                            nextNode.addConnection(currNode)

                            currPos = nextPos
                        }
                        "(" -> {
                            posStack.add(currPos)
                        }
                        "|" -> {
                            currPos = posStack.last()
                        }
                        ")" -> {
                            currPos = posStack.last()
                            posStack.removeAt(posStack.size - 1)
                        }
                        else -> throw IllegalArgumentException("Unknown character: \"$element\"")
                    }
                }

        return graph
    }

}