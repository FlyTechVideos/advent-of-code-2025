package day6

import InputReader

class Day6 {

    private val input = InputReader.readInput(6, test = false)

    fun solve1() {
        val splitInput = input.map { it.trim().split("\\s+".toRegex()) }

        val numbers = splitInput.dropLast(1)
            .map { row -> row.map { it.toLong() } }
        val operations = splitInput.takeLast(1)[0]

        val final = numbers[0].toMutableList()
        numbers.drop(1).forEach { row ->
            row.forEachIndexed { i, it ->
                if (operations[i] == "+") {
                    final[i] += it
                } else {
                    final[i] *= it
                }
            }
        }
        println(final.sum())
    }

    fun solve2() {
        var column = input.maxOfOrNull { it.length - 1 }!!
        val numbers = input.dropLast(1)
        val operations = input.takeLast(1)[0]

        var result = 0L
        val constructedNumbers = mutableListOf<Long>()
        while (column >= 0) {
            var number = ""
            numbers.forEach {
                number += it.getOrElse(column) { ' ' }
            }
            constructedNumbers.add(number.trim().toLong())
            val operator = operations.getOrNull(column)
            when (operator) {
                '+' -> {
                    result += constructedNumbers.sum()
                    constructedNumbers.clear()
                    column--
                }
                '*' -> {
                    result += constructedNumbers.fold(1L) { a, b -> a * b }
                    constructedNumbers.clear()
                    column--
                }
            }

            column--
        }
        println(result)
    }
}
