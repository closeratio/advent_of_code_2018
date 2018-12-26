package com.closeratio.aoc2018.day13

import com.closeratio.aoc2018.common.math.Vec2i
import com.closeratio.aoc2018.day13.Orientation.DOWN
import com.closeratio.aoc2018.day13.Orientation.RIGHT
import com.closeratio.aoc2018.day13.TrackType.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class SimulationTest {

	val sim = Simulation.from(javaClass.getResource("/test_input_1.txt").readText())

	@Test
	fun from() {
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
	fun iterate3Times() {
		val result = sim.iterate(3)
		assertNull(result)

		assertThat(sim.carts[0].position, `is`(Vec2i.from(4, 1)))
		assertThat(sim.carts[0].orientation, `is`(DOWN))

		assertThat(sim.carts[1].position, `is`(Vec2i.from(11, 4)))
		assertThat(sim.carts[1].orientation, `is`(RIGHT))
	}

	@Test
	fun iterateUntilCrash() {
		val result = sim.iterate(14)!!

		assertThat(result, `is`(Vec2i.from(7, 3)))
	}

}