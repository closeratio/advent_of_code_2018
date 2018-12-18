package com.closeratio.aoc2018.day12

data class Ruleset constructor(val rules: Map<RuleInput, RuleOutput>) {

	companion object {
		val RULE_REGEX = """([#.]{5}) => ([#.])""".toRegex()

		fun from(data: List<String>): Ruleset {
			return Ruleset(data.subList(2, data.size).map {
				val matchedValues = RULE_REGEX.find(it)!!.groupValues
				val inputValues = matchedValues[1]
				val outputValue = matchedValues[2]

				Pair(
						RuleInput(
								inputValues[0] == '#',
								inputValues[1] == '#',
								inputValues[2] == '#',
								inputValues[3] == '#',
								inputValues[4] == '#'),
						RuleOutput(
								outputValue == "#")
				)
			}.associate { it })
		}
	}
}