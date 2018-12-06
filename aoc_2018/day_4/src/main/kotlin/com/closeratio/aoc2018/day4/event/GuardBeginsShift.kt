package com.closeratio.aoc2018.day4.event

import com.closeratio.aoc2018.day4.guard.GuardId
import java.time.LocalDateTime

class GuardBeginsShift(
		dateTime: LocalDateTime,
		val guardId: GuardId
): Event(dateTime)