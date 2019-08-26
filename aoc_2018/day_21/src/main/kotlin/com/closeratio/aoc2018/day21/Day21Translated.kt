package com.closeratio.aoc2018.day21

import kotlin.system.exitProcess

class Day21Translated {

    var reg0 = 0
    var reg1 = 0
    var reg2 = 0
    var reg3 = 0
    var reg4 = 0
    var reg5 = 0

    private fun exitCheck() {
        // 29
        reg4 = if (reg3 == reg0) 1 else 0

        // 30
        if (reg4 == 1) {
            exitProcess(0)
        }

        // 31 (effectively, by returning to mainRoutine()
    }

    private fun procB() {
        // 18
        reg4 = 0
        // 19
        reg2 = reg4 + 1
        // 20
        reg2 *= 256
        // 21
        reg2 = if (reg2 > reg1) 1 else 0
        // 22, 23, 24
        if (reg2 == 1) {
            procC()
        } else {
            procD()
        }
    }

    private fun procC() {
        // 27
        reg1 = reg4
        // 28
        procE()
    }

    private fun procD() {
        // 25
        reg4 += 1
        // 26

    }

    private fun procE() {
        // 9
        reg4 = reg1.and(255)
        // 10
        reg3 += reg4
        // 11
        reg3 = reg3.and(16777215)
        // 12
        reg3 *= 65899
        // 13
        reg3 = reg3.and(16777215)
        // 14
        reg4 = if (256 > reg1) 1 else 0
        // 15, 16, 17
        if (reg4 == 1) {
            exitCheck()
        } else {
            procB()
        }
    }

    fun init() {
        // 6
        reg3 = 0

        mainRoutine()
    }

    private fun mainRoutine() {
        while (true) {
            // 7
            reg1 = reg3.or(65536)
            // 8
            reg3 = 9450265

            procE()
        }
    }

}