package com.closeratio.aoc2018.day8

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class LicenseTest {

	@Test
	fun from() {
		val license = License.from(loadResource("/test_input_1.txt").data)

		assertThat(license.root.children.size, `is`(2))
		assertThat(license.root.metadata.size, `is`(3))
		assertThat(license.root.metadata[0].value, `is`(1))
		assertThat(license.root.metadata[1].value, `is`(1))
		assertThat(license.root.metadata[2].value, `is`(2))

		assertThat(license.root.children[0].children.size, `is`(0))
		assertThat(license.root.children[0].metadata.size, `is`(3))
		assertThat(license.root.children[0].metadata[0].value, `is`(10))
		assertThat(license.root.children[0].metadata[1].value, `is`(11))
		assertThat(license.root.children[0].metadata[2].value, `is`(12))

		assertThat(license.root.children[1].children.size, `is`(1))
		assertThat(license.root.children[1].metadata.size, `is`(1))
		assertThat(license.root.children[1].metadata[0].value, `is`(2))

		assertThat(license.root.children[1].children[0].children.size, `is`(0))
		assertThat(license.root.children[1].children[0].metadata.size, `is`(1))
		assertThat(license.root.children[1].children[0].metadata[0].value, `is`(99))
	}

	@Test
	fun metadataSum() {
		val license = License.from(loadResource("/test_input_1.txt").data)

		assertThat(license.metadataSum(), `is`(138))
	}

	@Test
	fun computeValue() {
		val license = License.from(loadResource("/test_input_1.txt").data)

		assertThat(license.computeValue(), `is`(66))
	}

}