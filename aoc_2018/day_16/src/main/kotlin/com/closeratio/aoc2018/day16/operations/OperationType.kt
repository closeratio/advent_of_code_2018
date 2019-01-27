package com.closeratio.aoc2018.day16.operations

enum class OperationType(
		val operation: Operation
) {

	ADD_IMMEDIATE(AddImmediate()),
	ADD_REGISTER(AddRegister()),
	BITWISE_AND_IMMEDIATE(BitwiseAndImmediate()),
	BITWISE_AND_REGISTER(BitwiseAndRegister()),
	BITWISE_OR_IMMEDIATE(BitwiseOrImmediate()),
	BITWISE_OR_REGISTER(BitwiseOrRegister()),
	EQUAL_IMMEDIATE_REGISTER(EqualImmediateRegister()),
	EQUAL_REGISTER_IMMEDIATE(EqualRegisterImmediate()),
	EQUAL_REGISTER_REGISTER(EqualRegisterRegister()),
	GREATER_THAN_IMMEDIATE_REGISTER(GreaterThanImmediateRegister()),
	GREATER_THAN_REGISTER_IMMEDIATE(GreaterThanRegisterImmediate()),
	GREATER_THAN_REGISTER_REGISTER(GreaterThanRegisterRegister()),
	MULTIPLY_IMMEDIATE(MultiplyImmediate()),
	MULTIPLY_REGISTER(MultiplyRegister()),
	SET_IMMEDAITE(SetImmediate()),
	SET_REGISTER(SetRegister())

}