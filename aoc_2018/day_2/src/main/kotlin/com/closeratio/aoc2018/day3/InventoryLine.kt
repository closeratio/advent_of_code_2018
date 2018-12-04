package com.closeratio.aoc2018.day3

data class InventoryLine(
		val pairTotal: Int,
		val tripleTotal: Int
) {

	operator fun plus(other: InventoryLine) = InventoryLine(
			pairTotal + other.pairTotal,
			tripleTotal + other.tripleTotal)

}