package com.closeratio.aoc2018.day9

class Game(
		val playerCount: Int,
		val lastMarbleValue: Int
) {

	fun simulate(): List<Player> {
		val players = IntRange(1, playerCount).map { Player(it) }

		var marbleValue = 1L
		var playerIndex = 0

		var currentMarble = Marble(0)
		currentMarble.next = currentMarble
		currentMarble.previous = currentMarble

		while (marbleValue <= lastMarbleValue) {
			val player = players[playerIndex]
			val marbleToInsert = Marble(marbleValue)

			if (marbleValue % 23 == 0L) {
				player.marbles.add(marbleToInsert)

				val marbleToRemove = currentMarble
						.previous
						.previous
						.previous
						.previous
						.previous
						.previous
						.previous

				val leftMarble = marbleToRemove.previous
				val rightMarble = marbleToRemove.next

				player.marbles.add(marbleToRemove)

				leftMarble.next = rightMarble
				rightMarble.previous = leftMarble
				currentMarble = rightMarble
			} else {
				val leftMarble = currentMarble.next
				val rightMarble = leftMarble.next

				leftMarble.next = marbleToInsert
				marbleToInsert.previous = leftMarble

				rightMarble.previous = marbleToInsert
				marbleToInsert.next = rightMarble

				currentMarble = marbleToInsert
			}

//			val sb = StringBuilder("[${player.id}] ")
//			sb.append(" 0 ")
//
//			var currMarble = zeroMarble.next
//			while (currMarble != zeroMarble) {
//
//				if (currMarble == currentMarble) {
//					sb.append("(${currMarble.value})")
//				} else {
//					sb.append(" ${currMarble.value} ")
//				}
//				currMarble = currMarble.next
//			}
//			println(sb)

			playerIndex = (playerIndex + 1) % players.size
			++marbleValue
		}

		return players
	}

	fun getHighestScore(): Long {
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