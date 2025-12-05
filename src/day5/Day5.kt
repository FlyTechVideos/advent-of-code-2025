package day5

import InputReader
import kotlin.math.max

class Day5 {

    private val input = InputReader.readInput(5, test = false, split = false)[0]
        .split("\n\n")

    private val ranges = input[0].split("\n").map {
        val parts = it.split("-")
        parts[0].toLong()..parts[1].toLong()
    }
    private val ingredients = input[1].split("\n").map {
        it.toLong()
    }

    fun solve1() {
        println(ingredients.count { i -> ranges.any { r -> r.contains(i) } })
    }

    fun solve2() {
        val sortedRanges = ranges.sortedBy { it.first }

        var countedNumbers = 0L
        var lastSeen = 0L
        sortedRanges.forEach {
            val minimum = max(it.first, lastSeen + 1)
            val adaptedRange = minimum..it.last
            if (adaptedRange.first <= adaptedRange.last) {
                countedNumbers += (adaptedRange.last - adaptedRange.first + 1)
                lastSeen = max(it.last, minimum)
            }
        }

        println(countedNumbers)
    }
}
