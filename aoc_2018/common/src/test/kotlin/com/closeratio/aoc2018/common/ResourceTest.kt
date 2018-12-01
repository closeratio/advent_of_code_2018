package com.closeratio.aoc2018.common

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ResourceTest {

	@Test
	fun tsvConversion() {
		val result = Resource("1\t2\t3\t4").parseTsv()

		assertThat(result.size, `is`(1))
		assertThat(result[0].elements.size, `is`(4))
		assertThat(result[0].elements[0], `is`("1"))
	}

}