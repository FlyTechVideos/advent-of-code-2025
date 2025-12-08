package day8

import InputReader
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class Day8 {

    private val input = InputReader.readInput(8, test = false)
        .map { row ->
            row.split(",").let {
                JunctionBox(it[0].toInt(), it[1].toInt(), it[2].toInt())
            }
        }

    data class JunctionBox(val x: Int, val y: Int, val z: Int) {
        fun distance(other: JunctionBox) = sqrt(
            (x.toDouble() - other.x).pow(2.0) +
                    (y.toDouble() - other.y).pow(2.0) +
                    (z.toDouble() - other.z).pow(2.0)
        )
    }

    data class Distance(val i1: Int, val i2: Int, val distance: Double): Comparable<Distance> {
        override fun compareTo(other: Distance) = distance.compareTo(other.distance)
    }

    fun solve1() {
        val distances = mutableListOf<Distance>()
        input.forEachIndexed { ia, a ->
            input.forEachIndexed { ib, b ->
                if (ia < ib) {
                    distances.add(Distance(ia, ib, a.distance(b)))
                }
            }
        }
        distances.sort()

        var currentCircuitId = 0
        val circuitAssignment = MutableList<Int?>(input.size) { null }
        distances.take(1000).forEach { (i1, i2, _) ->
            val c1 = circuitAssignment[i1]
            val c2 = circuitAssignment[i2]
            if (c1 == null || c2 == null) {
                if (c1 != null) {
                    circuitAssignment[i2] = c1
                } else if (c2 != null) {
                    circuitAssignment[i1] = c2
                } else {
                    val newAssignment = currentCircuitId++
                    circuitAssignment[i1] = newAssignment
                    circuitAssignment[i2] = newAssignment
                }
            } else if (c1 != c2) {
                val retain = min(c1, c2)
                val remove = max(c1, c2)

                circuitAssignment[i1] = retain
                circuitAssignment[i2] = retain

                circuitAssignment.replaceAll { i -> if (i == remove) retain else i }
            }
        }

        val countsMap = circuitAssignment.groupBy { it }
            .filter { (key, _) -> key != null }
            .map { (key, list) -> key to list.size }
            .sortedByDescending { it.second }
        println(countsMap.take(3).map { it.second }.fold(1) { a, b -> a * b })
    }

    fun solve2() {
        val distances = mutableListOf<Distance>()
        input.forEachIndexed { ia, a ->
            input.forEachIndexed { ib, b ->
                if (ia < ib) {
                    distances.add(Distance(ia, ib, a.distance(b)))
                }
            }
        }
        distances.sort()

        var currentCircuitId = 0
        var countOfAssignedIds = 0
        var countOfRemovedIds = 0
        val circuitAssignment = MutableList<Int?>(input.size) { null }
        distances.forEach { (i1, i2, _) ->
            val c1 = circuitAssignment[i1]
            val c2 = circuitAssignment[i2]
            if (c1 == null || c2 == null) {
                if (c1 != null) {
                    circuitAssignment[i2] = c1
                    countOfAssignedIds++
                } else if (c2 != null) {
                    circuitAssignment[i1] = c2
                    countOfAssignedIds++
                } else {
                    val newAssignment = currentCircuitId++
                    circuitAssignment[i1] = newAssignment
                    circuitAssignment[i2] = newAssignment
                    countOfAssignedIds += 2
                }
                if (countOfAssignedIds == input.size && countOfRemovedIds == currentCircuitId - 1&& countOfRemovedIds == currentCircuitId - 1) {
                    println(input[i1].x.toLong() * input[i2].x)
                    return
                }
            } else if (c1 != c2) {
                val retain = min(c1, c2)
                val remove = max(c1, c2)

                circuitAssignment[i1] = retain
                circuitAssignment[i2] = retain

                circuitAssignment.replaceAll { i -> if (i == remove) retain else i }
                countOfRemovedIds++

                if (retain == 0 && countOfRemovedIds == currentCircuitId - 1 && countOfAssignedIds == input.size) {
                    println(input[i1].x.toLong() * input[i2].x)
                    return
                }
            }
        }
    }
}
