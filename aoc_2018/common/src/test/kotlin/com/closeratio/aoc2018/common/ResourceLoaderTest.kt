package com.closeratio.aoc2018.common

import com.closeratio.aoc2018.common.resource.ResourceLoader.loadResource
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.junit.jupiter.api.Test

class ResourceLoaderTest {

	@Test
	fun loadResource() {

		assertThat(loadResource("/test_tsv_resource.txt").data.length, greaterThan(0))
	}

}