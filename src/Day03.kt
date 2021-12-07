fun main() {
    fun part1(inputs: List<String>): Int {
        val half = inputs.size / 2
        val bitCount = IntArray(inputs.first().length) { 0 }
        for (input in inputs) {
            input.map { it.digitToInt() }.forEachIndexed { index, byte -> bitCount[index] += byte }
        }
        val gammaRate = bitCount.map { if (it < half) 0 else 1}.joinToString("")
        val epsilonRate = gammaRate.map { if (it == '0') '1' else '0' }.joinToString("")
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun findOxygenRating(inputs : List<String>, index: Int = 0): Int {
        if (inputs.size == 1) return inputs[0].toInt(2)
        val filtered = inputs.partition { it[index] == '0' }.let { (zeros, ones) ->
            if (zeros.size > ones.size) zeros else ones
        }
        return findOxygenRating(filtered, index + 1)
    }

    fun findScrubberRating(inputs : List<String>, index: Int = 0): Int {
        if (inputs.size == 1) return inputs[0].toInt(2)
        val filtered = inputs.partition { it[index] == '0' }.let { (zeros, ones) ->
            if (zeros.size <= ones.size) zeros else ones
        }
        return findScrubberRating(filtered, index + 1)
    }

    fun part2(inputs: List<String>): Int {
        val oxygenRating = findOxygenRating(inputs)
        val scrubberRating = findScrubberRating(inputs)
        return oxygenRating * scrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
