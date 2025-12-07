package day7

import InputReader

class Day7 {

    private val input = InputReader.readInput(7, test = false)
        .map { it.trim().split("") }

    fun solve1() {
        val grid = input.map { it.toMutableList() }
        grid[0].replaceAll({ i -> if (i == "S") "|" else i })

        var splits = 0
        grid.dropLast(1).forEachIndexed { y, row ->
            row.forEachIndexed { x, it ->
                if (it == "|") {
                    if (grid[y + 1][x] == "^") {
                        grid[y + 1][x - 1] = "|"
                        grid[y + 1][x + 1] = "|"
                        splits++
                    } else {
                        grid[y + 1][x] = "|"
                    }
                }
            }
        }
        println(splits)
    }

    fun solve2() {
        val grid = input.map { it.toMutableList() }
        grid[0].replaceAll({ i -> if (i == "S") "1" else i })

        grid.dropLast(1).forEachIndexed { y, row ->
            row.forEachIndexed { x, it ->
                val cellNum = it.toLongOrNull()
                if (cellNum != null) {
                    if (grid[y + 1][x] == "^") {
                        addToValue(grid, y + 1, x - 1, cellNum)
                        addToValue(grid, y + 1, x + 1, cellNum)
                    } else {
                        addToValue(grid, y + 1, x, cellNum)
                    }
                }
            }
        }
        println(grid.last().mapNotNull { it.toLongOrNull() }.sum())
    }

    private fun addToValue(
        grid: List<MutableList<String>>,
        y: Int,
        x: Int,
        cellNum: Long
    ) {
        if (grid[y][x] == ".") {
            grid[y][x] = cellNum.toString()
        } else {
            grid[y][x] = (grid[y][x].toLong() + cellNum).toString()
        }
    }
}
