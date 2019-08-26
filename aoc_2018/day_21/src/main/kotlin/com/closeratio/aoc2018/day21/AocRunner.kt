package com.closeratio.aoc2018.day21

import com.closeratio.aoc2018.day19.Computer

object AocRunner {

    val computer = Computer.from(javaClass
            .getResource("/day21_input.txt")
            .readText()
            .trim()
            .split("\n")
            .map { it.trim() })
    val validValues = linkedSetOf<Int>()

    fun runPart1() {
        // Add pre-exec callback
        computer.addPreExecCallback(28) {
            println(computer.registers[3])
            true
        }

        computer.executeUntilFinished()
    }

    fun runPart2() {
        // Add pre-exec callback
        computer.addPreExecCallback(28) {
            val target = computer.registers[3]
            if (target in validValues) {
                println(validValues.last())
                true
            } else {
                validValues.add(target)
                false
            }
        }
    }

}

fun main() {
    AocRunner.runPart2()
}