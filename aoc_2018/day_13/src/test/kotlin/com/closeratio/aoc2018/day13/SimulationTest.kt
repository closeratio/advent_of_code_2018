package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day13.Orientation.DOWN
import com.closeratio.aoc2018.day13.Orientation.RIGHT
import com.closeratio.aoc2018.day13.TrackType.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class SimulationTest {

	@Test
	fun from() {
		val sim = Simulation.from(javaClass.getResource("/test_input_1.txt").readText())
		assertThat(sim.carts.size, `is`(2))

		assertThat(sim.carts[0].orientation, `is`(RIGHT))
		assertThat(sim.carts[0].position, `is`(Vec2i.from(2, 0)))

		assertThat(sim.carts[1].orientation, `is`(DOWN))
		assertThat(sim.carts[1].position, `is`(Vec2i.from(9, 3)))

		assertThat(sim.tracks.size, `is`(48))
		assertThat(sim.trackMap[Vec2i.from(0, 0)]!!.trackType, `is`(FORWARD_SLASH))
		assertThat(sim.trackMap[Vec2i.from(1, 4)]!!.trackType, `is`(HORIZONTAL))
		assertThat(sim.trackMap[Vec2i.from(4, 2)]!!.trackType, `is`(CROSSROADS))
		assertThat(sim.trackMap[Vec2i.from(7, 3)]!!.trackType, `is`(VERTICAL))
		assertThat(sim.trackMap[Vec2i.from(9, 2)]!!.trackType, `is`(BACKWARD_SLASH))
	}
	@Test
	fun iterateUntilCrash() {
		val sim = Simulation.from(javaClass.getResource("/test_input_1.txt").readText())
		val result = sim.iterateUntilCrash()

		assertThat(result, `is`(Vec2i.from(7, 3)))
	}

	@Test
	fun iterateUntilSingleCartLeft() {
		val sim = Simulation.from(javaClass.getResource("/test_input_2.txt").readText())
		val result = sim.iterateUntilSingleCartLeft()

		assertThat(result, `is`(Vec2i.from(6, 4)))
	}

}