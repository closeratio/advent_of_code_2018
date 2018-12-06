package com.closeratio.aoc2018.day4

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import com.closeratio.aoc2018.day4.event.EventParser.parseEvent
import com.closeratio.aoc2018.day4.event.GuardBeginsShift
import com.closeratio.aoc2018.day4.event.GuardWakesUp
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class EventParserTest {

	@Test
	fun input1Test() {
		val events = loadResource("/test_input_1.txt")
				.data
				.trim()
				.split("\n")
				.map { parseEvent(it) }
				.sortedBy { it.dateTime }

		assertThat(events.size, `is`(17))

		assertThat(events[0], `is`(instanceOf(GuardBeginsShift::class.java)))
		assertThat(events[0].dateTime, `is`(LocalDateTime.of(1518, 11, 1, 0, 0)))

		assertThat(events[16], `is`(instanceOf(GuardWakesUp::class.java)))
		assertThat(events[16].dateTime, `is`(LocalDateTime.of(1518, 11, 5, 0, 55)))
	}

}