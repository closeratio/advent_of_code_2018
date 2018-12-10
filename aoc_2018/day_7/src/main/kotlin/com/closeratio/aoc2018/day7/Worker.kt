package com.closeratio.aoc2018.day7

class Worker(
		val id: Int
) {

	var currentStep: Step? = null
	var secondsOnCurrentStep: Int = 0

	fun iterate() {
		secondsOnCurrentStep++
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Worker

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id
	}

	override fun toString(): String {
		return "Worker(id=$id, currentStep=$currentStep)"
	}


}