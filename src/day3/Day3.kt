package day3

import InputReader

class Day3 {

    private val input = InputReader.readInput(3, test = false)

    fun solve1() {
        println(input.sumOf { bank -> findMaxJoltage(bank, 2) })
    }

    private fun findMaxJoltage(bank: String, i: Int, str: String = ""): Long {
        if (i == 0) {
            return str.toLong()
        }

        var highestDigit = '0' - 1
        return bank.dropLast(i - 1).mapIndexed { index, ch ->
            if (ch > highestDigit) {
                highestDigit = ch
                findMaxJoltage(bank.substring(index + 1), i - 1, "$str$ch")
            } else {
                0
            }
        }.max()
    }

    fun solve2() {
        println(input.sumOf { bank -> findMaxJoltage(bank, 12) })
    }
}
