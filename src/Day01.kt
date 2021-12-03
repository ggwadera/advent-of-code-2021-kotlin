fun main() {
    fun part1(input: List<Int>): Int =
        input.windowed(size = 2) { (left, right) ->
            if (right > left) 1 else 0
        }.sum()

    fun part2(input: List<Int>): Int =
        input.windowed(size = 4) { window ->
            // From the example, we need to compare window B against window A
            // B > A ?
            // 210 + 208 + 200 > 208 + 200 + 199
            // which simplifies to 210 > 199 = true
            // By taking a window of 4 elements we only need to compare the first and last elements
            if (window[3] > window[0]) 1 else 0
        }.sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map{ it.toInt() }
    check(part1(testInput) == 7)

    val input = readInput("Day01").map{ it.toInt() }
    println(part1(input))
    println(part2(input))
}
