package com.closeratio.aoc2018.day14

class RecipeSimulation private constructor(
		val recipes: ArrayList<Recipe>,
		val elves: List<Elf>) {

	fun iterateUntilLength(terminationLength: Int): String {
		while (recipes.size < terminationLength + 10) {
			iterate()
		}

		return recipes.subList(terminationLength, terminationLength + 10).joinToString("") { it.score.toString() }
	}

	private fun iterate() {
		// Add new score
		val newScore = elves.map { elf ->
			recipes[elf.currentIndex].score
		}.sum()

		recipes.addAll(newScore.toString()
				.map { it.toString().toInt() }
				.map { Recipe(it) })

		// Iterate elves
		elves.map { elf ->
			elf.currentIndex = (elf.currentIndex + recipes[elf.currentIndex].score + 1) % recipes.size
		}

	}

	companion object {
		fun create(): RecipeSimulation {
			return RecipeSimulation(
					arrayListOf(
							Recipe(3),
							Recipe(7)),
					listOf(
							Elf(0, 0),
							Elf(1, 1)))
		}
	}
}