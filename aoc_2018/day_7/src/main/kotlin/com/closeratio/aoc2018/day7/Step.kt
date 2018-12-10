package com.closeratio.aoc2018.day7

class Step(
		val name: Char,
		val priorSteps: HashSet<Step>,
		val subsequentSteps: HashSet<Step>,
		val executionTime: Int
) {

	override fun toString(): String {
		return "Step(name='$name')"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Step

		if (name != other.name) return false

		return true
	}

	override fun hashCode(): Int {
		return name.hashCode()
	}
}
