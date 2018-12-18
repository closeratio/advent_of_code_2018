package com.closeratio.aoc2018.day12

import com.closeratio.aoc2018.common.resource.ResourceLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.junit.jupiter.api.Test

class RulesetTest {

	@Test
	fun from() {
		val result = Ruleset.from(ResourceLoader.loadResource("/test_input_1.txt").data.split("\n"))

		assertThat(result.rules.size, `is`(14))
		assertThat(
				result.rules,
				hasEntry(
						RuleInput(false, true, true, false, false),
						RuleOutput(true)))

		assertThat(
				result.rules,
				hasEntry(
						RuleInput(true, true, true, false, false),
						RuleOutput(true)))

		assertThat(
				result.rules,
				hasEntry(
						RuleInput(false, false, false, true, true),
						RuleOutput(true)))
	}
}