package com.closeratio.aoc2018.day9

import com.closeratio.aoc2018.common.resource.ResourceLoader
import com.closeratio.aoc2018.day9.Game.Companion.from
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class GameTest {

	@Test
	fun from() {
		val game1 = from(ResourceLoader.loadResource("/test_input_1.txt").data)
		assertThat(game1.playerCount, `is`(10))
		assertThat(game1.lastMarbleValue, `is`(1618))

		val game2 = from(ResourceLoader.loadResource("/test_input_2.txt").data)
		assertThat(game2.playerCount, `is`(13))
		assertThat(game2.lastMarbleValue, `is`(7999))
	}

	@Test
	fun getHighestScoreWithInput1() {
		val game = from(ResourceLoader.loadResource("/test_input_1.txt").data)
		val result = game.getHighestScore()

		assertThat(result, `is`(8317L))
	}

	@Test
	fun getHighestScoreWithInput2() {
		val game = from(ResourceLoader.loadResource("/test_input_2.txt").data)
		val result = game.getHighestScore()

		assertThat(result, `is`(146373L))
	}

	@Test
	fun getHighestScoreWithInput6() {
		val game = from(ResourceLoader.loadResource("/test_input_6.txt").data)
		val result = game.getHighestScore()

		assertThat(result, `is`(32L))
	}

}