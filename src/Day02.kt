fun main() {
    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        for (command in input) {
            val split = command.split(" ")
            val direction = split[0]
            val units  = split[1].toInt()
            when(direction) {
                "forward" -> horizontal += units
                "up" -> depth -= units
                "down" -> depth += units
            }
        }
        return horizontal * depth
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        for (command in input) {
            val split = command.split(" ")
            val direction = split[0]
            val units  = split[1].toInt()
            when(direction) {
                "forward" -> {
                    horizontal += units
                    depth += aim * units
                }
                "up" -> aim -= units
                "down" -> aim += units
            }
        }
        return horizontal * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
