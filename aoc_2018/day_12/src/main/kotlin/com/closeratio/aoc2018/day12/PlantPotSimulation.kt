package com.closeratio.aoc2018.day12

class PlantPotSimulation private constructor(
		initialState: PlantPotState,
		val ruleset: Ruleset
) {

	var state = initialState
	private val transitionCache = TransitionCache()

	fun iterate(iterCount: Long, useLoopDetection: Boolean = true) {

		LongRange(1, iterCount).forEach { currIter ->
			val cache = transitionCache.cache

			val cachePreSize = cache.size
			state = state.produceNewState(ruleset, transitionCache)
			val cachePostSize = cache.size

			// Detect loop
			if (useLoopDetection && cachePreSize == cachePostSize) {

				val loopStartIndex = cache.keys.indexOf(state.states)
				val singleLoopSize = cache.size - loopStartIndex

				val singleLoopOffset = cache.values
						.toList()
						.subList(loopStartIndex, cache.size)
						.map { it.indexOffset }
						.sum()

				val loopsRequired = (iterCount - currIter) / singleLoopSize
				val loopOffset = loopsRequired * singleLoopOffset + state.baseIndex
				state = PlantPotState.from(loopOffset, state.states)

				return
			}
		}
	}

	fun sumOfPlantIndices(): Long {
		val base = state.baseIndex

		return state.states
				.mapIndexed { index, state ->
					if (state) index + base else 0
				}
				.sum()
	}

	companion object {
		fun from(data: List<String>): PlantPotSimulation {
			return PlantPotSimulation(
					PlantPotState.from(data),
					Ruleset.from(data))
		}
	}
}