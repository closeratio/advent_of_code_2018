package com.closeratio.aoc2018.day14

class RecipeSimulation private constructor(
		val recipes: StringBuilder,
		val elves: List<Elf>) {

	fun iterateUntilLength(terminationLength: Int): String {
		while (recipes.length < terminationLength + 10) {
			iterate()
		}

		val recipeString = recipes.toString()

		return recipeString.substring(terminationLength, terminationLength + 10)
	}

	fun iterateUntilStringAppears(stringToCheck: String): Int {
		while (recipes.length < stringToCheck.length) {
			iterate()
		}

		while (!recipes.substring(recipes.length - stringToCheck.length).contains(stringToCheck)) {
			iterate()
		}

		val recipeString = recipes.toString()
		return recipeString.indexOf(stringToCheck)
	}

	private fun iterate() {
		// Add new score
		val newScore = elves.map { elf ->
			recipes[elf.currentIndex].toString().toInt()
		}.sum()

		recipes.append(newScore.toString())

		// Iterate elves
		elves.map { elf ->
			elf.currentIndex = (elf.currentIndex + recipes[elf.currentIndex].toString().toInt() + 1) % recipes.length
		}

	}

	companion object {
		fun create(): RecipeSimulation {
			return RecipeSimulation(
					StringBuilder("37"),
					listOf(
							Elf(0, 0),
							Elf(1, 1)))
		}
	}
}