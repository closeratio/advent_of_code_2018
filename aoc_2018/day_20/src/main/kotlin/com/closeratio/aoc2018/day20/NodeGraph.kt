package com.closeratio.aoc2018.day20

import com.closeratio.aoc2018.common.math.Vec2d
import com.closeratio.aoc2018.common.math.Vec2i

class NodeGraph {

    val nodes: HashMap<Vec2i, Node> = hashMapOf()

    fun addNodeIfNecessary(pos: Vec2i): Node = nodes.getOrPut(pos) { Node(pos) }

    fun calculateDistanceMap(): Map<Node, Int> {
        val start = nodes.getValue(Vec2i.ZERO)
        val distanceMap = hashMapOf(
                start to 0
        )

        val unprocessedNodes = hashSetOf(start)
        val processedNodes = hashMapOf<Node, Int>()

        while (unprocessedNodes.isNotEmpty()) {
            // Get next nearest room
            val curr = distanceMap
                    .entries
                    .filter { it.key in unprocessedNodes }
                    .minBy { it.value }!!
                    .key

            // Get distance of current room
            val currDist = distanceMap.getValue(curr)

            // Remove current room from unprocessed list, save its distance to the processed nodes map, and remove
            // it from the distance map so that it's doesn't get considered in the next iteration.
            unprocessedNodes.remove(curr)
            processedNodes[curr] = currDist
            distanceMap.remove(curr)

            curr.connectedNodes
                    .filter { it !in processedNodes }
                    .forEach {
                        val nextDist = currDist + 1
                        if (it !in distanceMap) {
                            distanceMap[it] = nextDist
                            unprocessedNodes.add(it)
                        } else if (nextDist < distanceMap.getValue(it)) {
                            distanceMap[it] = nextDist
                        }
                    }
        }

        return processedNodes
    }

    fun calculateFurthestNode(): SearchResult {

        val furthest = calculateDistanceMap()
                .entries
                .maxBy { it.value }!!

        return SearchResult(furthest.key, furthest.value)
    }

    fun printGraph(): String {
        val horizontalOffset = Vec2d.from(0.5, 0)
        val verticalOffset = Vec2d.from(0, 0.5)

        // Build a map of positions
        val posMap = nodes.values
                .flatMap {
                    val leftConnected = Node(it.position.left()) in it.connectedNodes
                    val rightConnected = Node(it.position.right()) in it.connectedNodes
                    val upConnected = Node(it.position.up()) in it.connectedNodes
                    val downConnected = Node(it.position.down()) in it.connectedNodes

                    val pos = it.position.toVec2d()

                    // Add the position as well as the eight surrounding positions. Many of these will be duplicated
                    // with adjacent positions, but the reduction to a map will remove the duplicates.
                    listOf(
                            pos to if (pos == Vec2d.ZERO) "X" else ".",
                            pos + (horizontalOffset * -1) to if (leftConnected) "|" else "#",
                            pos + horizontalOffset to if (rightConnected) "|" else "#",
                            pos + (verticalOffset * -1) to if (upConnected) "-" else "#",
                            pos + verticalOffset to if (downConnected) "-" else "#",
                            pos + horizontalOffset + verticalOffset to "#",
                            pos + horizontalOffset - verticalOffset to "#",
                            pos - horizontalOffset + verticalOffset to "#",
                            pos - horizontalOffset - verticalOffset to "#"
                    )
                }
                .toMap()

        val minX = posMap.keys.map { it.x }.min()!!
        val minY = posMap.keys.map { it.y }.min()!!

        return posMap.keys
                .sortedBy { it.x }
                .sortedBy { it.y }
                .joinToString("") {
                    if (it.x == minX && it.y > minY) {
                        "\n" + posMap.getValue(it)
                    } else {
                        posMap.getValue(it)
                    }
                }
    }

}
