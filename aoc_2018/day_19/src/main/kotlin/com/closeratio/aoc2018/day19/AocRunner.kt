package com.closeratio.aoc2018.day19

object AocRunner {

    val computer = Computer.from(javaClass.getResource("/input.txt")
            .readText()
            .split("\n")
            .map { it.trim() }
            .filter { it.isNotBlank() })

    fun runPart1() {
        computer.executeUntilFinished()
        println(computer.registers[0])
    }

    fun runPart2() {
        computer.registers[0] = 1

        computer.executeUntilFinished()
        println(computer.registers[0])
    }

    fun runTranslatedPart2() {
        val target = 10551389
        var total = 0
        var current = 1

        while (current <= (target / 2)) {
            if (target % current == 0) {
                total += current
            }

            current++
        }

        total += target

        println(total)
    }

}

fun main() {
    AocRunner.runTranslatedPart2()
}