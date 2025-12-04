package day4

import InputReader

class Day4 {

    private val input = InputReader.readInput(4, test = false)
        .map { it.map { if (it == '@') 1 else 0 } }

    private val grid = listOf(List(input[0].size + 2, { 0 }).toMutableList()) +
            input.map { (listOf(0) + it + listOf(0)).toMutableList() } +
            listOf(List(input[0].size + 2, { 0 }).toMutableList())

    fun solve1() {
        val height = grid.size
        val width = grid[0].size

        val result = (1 until height - 1).sumOf { y ->
            (1 until width - 1).count { x ->
                if (grid[y][x] == 0) {
                    return@count false
                }

                canBeAccessed(y, x)
            }
        }
        println(result)
    }

    private fun canBeAccessed(y: Int, x: Int): Boolean = grid[y - 1][x - 1] +
            grid[y - 1][x] +
            grid[y - 1][x + 1] +
            grid[y][x - 1] +
            grid[y][x + 1] +
            grid[y + 1][x - 1] +
            grid[y + 1][x] +
            grid[y + 1][x + 1] < 4

    fun solve2() {
        val height = grid.size
        val width = grid[0].size

        var previousRolls = 0
        var currentRolls = countRolls()
        var removedRolls = 0

        while (previousRolls != currentRolls) {
            val pairs = (1 until height - 1).flatMap { y ->
                (1 until width - 1).mapNotNull { x ->
                    if (grid[y][x] == 0) {
                        return@mapNotNull null
                    }

                    if (!canBeAccessed(y, x)) {
                        return@mapNotNull null
                    }

                    Pair(x, y)
                }
            }
            previousRolls = currentRolls
            currentRolls -= pairs.size
            removedRolls += pairs.size
            pairs.forEach { (x, y) -> grid[y][x] = 0 }
        }
        println(removedRolls)
    }

    private fun countRolls() = grid.sumOf { it.sum() }
}
