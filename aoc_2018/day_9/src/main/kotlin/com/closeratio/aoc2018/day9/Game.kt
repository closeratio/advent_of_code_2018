package com.closeratio.aoc2018.day9

class Game(
		val playerCount: Int,
		val lastMarbleValue: Int
) {

	fun simulate(): List<Player> {
		val players = IntRange(1, playerCount).map { Player(it) }

		var marbleValue = 1
		var playerIndex = 0

		val marbleCircle = arrayListOf(Marble(0))
		var currentMarbleIndex = 0

		while (marbleValue <= lastMarbleValue) {
			val player = players[playerIndex]
			val marbleToInsert = Marble(marbleValue)

			if (marbleValue % 23 == 0) {
				player.marbles.add(marbleToInsert)

				val removalIndex = Math.abs((currentMarbleIndex - 7) % marbleCircle.size)
				player.marbles.add(marbleCircle.removeAt(removalIndex))

				currentMarbleIndex = removalIndex
			} else {
				val insertionIndex = (currentMarbleIndex + 2) % marbleCircle.size
				marbleCircle.add(insertionIndex, marbleToInsert)
				currentMarbleIndex = insertionIndex
			}

//			val sb = StringBuilder("[${player.id}] ")
//			marbleCircle.forEachIndexed { index, marble ->
//				if (index == currentMarbleIndex) {
//					sb.append("(${marble.value})")
//				} else {
//					sb.append(" ${marble.value} ")
//				}
//			}
//			println(sb)

			playerIndex = (playerIndex + 1) % players.size
			++marbleValue
		}

		return players
	}

	fun getHighestScore(): Int {
		return simulate()
				.map { it.marbles.map { it.value }.sum() }
				.max()!!
	}

	companion object {
		val LINE_REGEX = """(\d+) players; last marble is worth (\d+) points""".toRegex()

		fun from(line: String): Game {
			val values = LINE_REGEX.find(line)!!.groupValues

			return Game(values[1].toInt(), values[2].toInt())
		}
	}
}