data class BoardNumber(val number: Int) {
    var marked = false
}

class Board(val numbers: List<List<BoardNumber>>) {

    var won = false

    fun markNumber(number: Int) {
        for (i in numbers.indices) {
            for (j in numbers[i].indices) {
                val boardNumber = numbers[i][j]
                if (boardNumber.number == number) {
                    boardNumber.marked = true
                    won = checkVictory(i, j)
                    return
                }
            }
        }
    }

    private fun checkVictory(row: Int, column: Int): Boolean {
        val rowComplete = numbers[row].all { it.marked }
        val columnComplete = numbers.all { it[column].marked }
        return rowComplete || columnComplete
    }

    fun sumUnmarked(): Int {
        return numbers.flatten().filter { !it.marked }.sumOf {it.number}
    }

}

fun main() {

    fun buildBoards(inputs: List<String>, boards: MutableList<Board>) {
        for (i in 2..inputs.size step 6) {
            val numbers: MutableList<List<BoardNumber>> = mutableListOf()
            for (j in i..i + 4) {
                inputs[j].trim().split("\\s+".toRegex())
                    .map { BoardNumber(it.toInt()) }
                    .let { numbers.add(it) }
            }
            boards.add(Board(numbers))
        }
    }

    fun part1(inputs: List<String>): Int {
        val drawnNumbers = inputs[0].split(",").map { it.toInt() }
        val boards: MutableList<Board> = mutableListOf()

        buildBoards(inputs, boards)

        for (draw in drawnNumbers) {
            for (board in boards) {
                board.markNumber(draw)
                if (board.won) return draw * board.sumUnmarked()
            }
        }

        println("Oops, should not have gotten here")
        return inputs.size
    }

    fun part2(inputs: List<String>): Int {
        val drawnNumbers = inputs[0].split(",").map { it.toInt() }
        val boards: MutableList<Board> = mutableListOf()

        buildBoards(inputs, boards)

        var finished = 0
        for (draw in drawnNumbers) {
            for (board in boards) {
                if (!board.won) {
                    board.markNumber(draw)
                    if (board.won) finished++
                }
                if (finished == boards.size) return board.sumUnmarked() * draw
            }
        }

        println("Oops, should not have gotten here")
        return inputs.size
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}