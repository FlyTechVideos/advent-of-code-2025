import java.lang.IllegalArgumentException

object InputReader {

    fun readInput(day: Int, test: Boolean = false, split: Boolean = true): List<String> {
        if (day !in 1..12) {
            throw IllegalArgumentException("AdventOfCode 2025+ only has 12 days.")
        }

        val resource = this.javaClass.getResource("/inputs/day$day${if (test) "_test" else ""}.txt")
            ?: throw IllegalArgumentException("File for day $day not found: day$day${if (test) "_test" else ""}.txt")

        return if (split) resource.readText().split("\r?\n".toRegex()).filter { it.isNotBlank() } else listOf(resource.readText().trim())
    }

    fun readIntLineInput(day: Int, test: Boolean = false): List<Int> {
        val stringList = readInput(day = day, test = test, split = true)
        return stringList.map { it.toInt() }
    }

}