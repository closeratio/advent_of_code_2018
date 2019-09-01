package com.closeratio.aoc2018.day23

// Credit to Todd Ginsberg (https://todd.ginsberg.com/post/advent-of-code/2018/day23/) for originally translating this
// algorithm into Kotlin.
object BronKerbosch {

    fun largestClique(nanobots: Set<Nanobot>): Set<Nanobot> {
        return execute(nanobots).maxBy { it.size }!!
    }

    private fun execute(
            p: Set<Nanobot>,
            r: Set<Nanobot> = emptySet(),
            x: Set<Nanobot> = emptySet()
    ): List<Set<Nanobot>> {
        return if (p.isEmpty() && x.isEmpty()) {
            listOf(r)
        } else {
            val mostNeighborsOfPandX: Nanobot = (p + x).maxBy { it.neighbours.size }!!
            val pWithoutNeighbors = p.minus(mostNeighborsOfPandX.neighbours)
            pWithoutNeighbors
                    .flatMap { nanobot ->
                        execute(
                                p.intersect(nanobot.neighbours),
                                r + nanobot,
                                x.intersect(nanobot.neighbours)
                        )
                    }
        }
    }
}