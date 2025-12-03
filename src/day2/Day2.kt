package day2

import InputReader
import kotlin.math.pow

class Day2 {

    private val input = InputReader.readInput(2, test = false, split = false)[0].split(",")
        .map {
            val split = it.split("-")
            Pair(split[0], split[1])
        }

    fun solve1() {
        val x = input.filter {
            it.first.length != it.second.length || it.first.length % 2L == 0L
        }.map {
            if (it.first.length != it.second.length) {
                if (it.first.length % 2L == 0L) {
                    Pair(it.first.toLong(), 10.0.pow(it.first.length).toLong() - 1)
                } else {
                    Pair(10.0.pow(it.second.length - 1).toLong(), it.second.toLong())
                }
            } else {
                Pair(it.first.toLong(), it.second.toLong())
            }
        }.sumOf {
            (it.first..it.second).filter {
                val str = it.toString()
                str.substring(0..<(str.length / 2)) == str.substring((str.length / 2))
            }.sum()
        }
        println(x)
    }

    fun solve2() {
        val periods = mapOf(
            1 to listOf(),
            2 to listOf(1),
            3 to listOf(1),
            4 to listOf(1, 2),
            5 to listOf(1),
            6 to listOf(1, 2, 3),
            7 to listOf(1),
            8 to listOf(1, 2, 4),
            9 to listOf(1, 3),
            10 to listOf(1, 2, 5)
        )

        val x = input.map {
            Pair(it.first.toLong(), it.second.toLong())
        }.sumOf { (p1, p2) ->
            (p1..p2).filter { num ->
                val str = num.toString()
                periods[str.length]!!.any {
                    str.chunked(it).toSet().size == 1
                }
            }.sum()
        }
        println(x)
    }
}