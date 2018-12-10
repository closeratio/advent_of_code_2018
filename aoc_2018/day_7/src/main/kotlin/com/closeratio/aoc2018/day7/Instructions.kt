package com.closeratio.aoc2018.day7

import java.util.*

class Instructions(
		val steps: List<Step>
) {


	fun stepOrder(): String {
		val openSteps = HashSet(steps)
		val closedSteps = linkedSetOf<Step>()

		while (openSteps.isNotEmpty()) {
			val step = openSteps.filter { closedSteps.containsAll(it.priorSteps) }
					.sortedBy { it.name }
					.first()

			openSteps.remove(step)
			closedSteps.add(step)
		}

		return closedSteps.map { it.name }.joinToString("")
	}

	fun stepOrderParallel(workerCount: Int): Pair<String, Int> {
		val workers = IntRange(1, workerCount).map { Worker(it) }

		val openSteps = HashSet(steps)
		val closedSteps = linkedSetOf<Step>()

		var iterationCount = 0

		while (!closedSteps.containsAll(steps)) {

			// Get available workers and steps
			val availableWorkers = LinkedList(workers.filter { it.currentStep == null })
			val availableSteps = LinkedList(openSteps
					.filter { closedSteps.containsAll(it.priorSteps) }
					.sortedBy { it.name })

			// Assign steps to workers
			while (availableWorkers.isNotEmpty() && availableSteps.isNotEmpty()) {
				val availableStep = availableSteps.pop()
				openSteps.remove(availableStep)

				val availableWorker = availableWorkers.pop()
				availableWorker.currentStep = availableStep
				availableWorker.secondsOnCurrentStep = 0
			}

			// Get working workers and iterate
			workers
					.filter { it.currentStep != null }
					.map {
						it.iterate()
						it
					}
					.filter { it.secondsOnCurrentStep >= it.currentStep!!.executionTime  }
					.forEach {
						closedSteps.add(it.currentStep!!)
						it.currentStep = null
						it.secondsOnCurrentStep = 0
					}

			++iterationCount
		}

		return Pair(
				closedSteps.map { it.name }.joinToString(""),
				iterationCount)
	}

	companion object {

		private val STEP_RULE_REGEX = """^Step (\w+) must be finished before step (\w+) can begin\.$""".toRegex()

		fun from(lines: List<String>, stepBaseTime: Int): Instructions {
			val stepMap = hashMapOf<Char, Step>()
			lines.forEach {
				val result = STEP_RULE_REGEX.find(it)!!
				val firstStepName = result.groupValues[1][0]
				val secondStepName = result.groupValues[2][0]

				if (firstStepName !in stepMap) stepMap[firstStepName] = Step(firstStepName, HashSet(), HashSet(), firstStepName.toInt() + stepBaseTime - 64)
				if (secondStepName !in stepMap) stepMap[secondStepName] = Step(secondStepName, HashSet(), HashSet(), secondStepName.toInt() + stepBaseTime - 64)

				stepMap[firstStepName]!!.subsequentSteps.add(stepMap[secondStepName]!!)
				stepMap[secondStepName]!!.priorSteps.add(stepMap[firstStepName]!!)
			}

			return Instructions(stepMap.values.toList())
		}
	}

}