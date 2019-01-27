package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.day16.operations.*

object MatchingOperationsChecker {

	val operations = arrayOf(
			AddImmediate(),
			AddRegister(),
			BitwiseAndImmediate(),
			BitwiseAndRegister(),
			BitwiseOrImmediate(),
			BitwiseOrRegister(),
			EqualImmediateRegister(),
			EqualRegisterImmediate(),
			EqualRegisterRegister(),
			GreaterThanImmediateRegister(),
			GreaterThanRefisterImmediate(),
			GreaterThanRegisterRegister(),
			MultiplyImmediate(),
			MultiplyRegister(),
			SetImmediate(),
			SetRegister())

	fun check(stateChange: StateChange): List<Operation> {
		return operations
				.filter {
					it.process(stateChange.preState, stateChange.instruction.parameters) == stateChange.postState
				}
	}

}