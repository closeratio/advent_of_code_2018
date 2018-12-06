package com.closeratio.aoc2018.day2

data class InventoryLine(
		val pairTotal: Int,
		val tripleTotal: Int
) {

	operator fun plus(other: InventoryLine) = InventoryLine(
			pairTotal + other.pairTotal,
			tripleTotal + other.tripleTotal)

}