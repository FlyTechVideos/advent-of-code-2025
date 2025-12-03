package day1

import InputReader
import kotlin.math.abs
import kotlin.math.floor

class Day1 {

    private val input = InputReader.readInput(1, test = false)

    fun solve1() {
        var value = 50
        var countZeroes = 0

        input.map {
            val direction = it[0]
            val amount = it.substring(1).toInt()

            Pair(direction, amount)
        }.forEach {
            if (it.first == 'L') {
                value -= it.second
            } else {
                value += it.second
            }

            value %= 100

            if (value == 0) {
                countZeroes++
            }
        }

        println(countZeroes)
    }

    fun solve2() {
        var value = 50
        var countZeroes = 0

        input.map {
            val direction = it[0]
            val amount = it.substring(1).toInt()

            Pair(direction, amount)
        }.forEach {
            val originalValueWasZero = value == 0
            if (it.first == 'L') {
                value -= it.second
            } else {
                value += it.second
            }

            countZeroes += floor(abs(value) / 100.0).toInt()
            if (value == 0 || (value < 0 && !originalValueWasZero)) {
                countZeroes++
            }

            value %= 100
            if (value < 0) {
                value += 100
            }
            print("")
        }

        println(countZeroes)
    }
}