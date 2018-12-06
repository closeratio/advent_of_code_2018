package com.closeratio.aoc2018.day4.event

import java.time.LocalDateTime

abstract class Event(val dateTime: LocalDateTime) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Event

		if (dateTime != other.dateTime) return false

		return true
	}

	override fun hashCode(): Int {
		return dateTime.hashCode()
	}

	override fun toString(): String {
		return "${javaClass.simpleName}(dateTime=$dateTime)"
	}
}