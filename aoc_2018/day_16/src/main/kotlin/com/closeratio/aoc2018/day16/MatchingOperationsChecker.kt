package com.closeratio.aoc2018.day16

import com.closeratio.aoc2018.day16.operations.Operation
import com.closeratio.aoc2018.day16.operations.OperationType

object MatchingOperationsChecker {

	private val operations = OperationType.values().map { it.operation }.toTypedArray()

	fun check(stateChange: StateChange): List<Operation> {
		return operations
				.filter {
					it.process(stateChange.preState, stateChange.instruction.parameters) == stateChange.postState
				}
	}

}