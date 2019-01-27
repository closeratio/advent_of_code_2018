package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day16.MatchingOperationsChecker.check
import com.closeratio.aoc2018.day16.StateChangeParser.parseStateChanges
import com.closeratio.aoc2018.day16.operations.AddImmediate
import com.closeratio.aoc2018.day16.operations.MultiplyRegister
import com.closeratio.aoc2018.day16.operations.SetImmediate
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.jupiter.api.Test

class MatchingOperationsCheckerTest {

	@Test
	fun check() {
		val result = check(parseStateChanges(ResourceLoader.loadResource("/test_state_changes_1.txt").data)[0])

		assertThat(result.size, `is`(3))

		assertThat(result, hasItem(MultiplyRegister()))
		assertThat(result, hasItem(AddImmediate()))
		assertThat(result, hasItem(SetImmediate()))
	}

}