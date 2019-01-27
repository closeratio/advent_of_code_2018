package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day16.StateChangeParser.parseStateChanges
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class StateChangeParserTest {

	@Test
	fun parse() {
		val result = parseStateChanges(ResourceLoader.loadResource("/test_state_changes_1.txt").data)

		assertThat(result.size, `is`(1))
		assertThat(result[0].preState, `is`(ProgramState(mapOf(
				0 to 3, 1 to 2, 2 to 1, 3 to 1
		))))
		assertThat(result[0].instruction, `is`(Instruction(
				9,
				OperationParameters(2, 1, 2)
		)))
		assertThat(result[0].postState, `is`(ProgramState(mapOf(
				0 to 3, 1 to 2, 2 to 2, 3 to 1
		))))
	}

}