package com.closeratio.aoc2018.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ElementTest {

	@Test
	fun reactsWith() {
		assertThat(Element('A').reactsWith(Element('a')), `is`(true))
		assertThat(Element('a').reactsWith(Element('A')), `is`(true))
		assertThat(Element('A').reactsWith(Element('A')), `is`(false))
		assertThat(Element('a').reactsWith(Element('a')), `is`(false))

		assertThat(Element('A').reactsWith(Element('b')), `is`(false))
		assertThat(Element('A').reactsWith(Element('B')), `is`(false))
		assertThat(Element('b').reactsWith(Element('b')), `is`(false))
	}

}