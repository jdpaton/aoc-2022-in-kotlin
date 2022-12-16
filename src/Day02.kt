
enum class Move{ ROCK, PAPER, SCISSORS }
enum class MoveNext { WIN, LOSE, DRAW }

data class MoveMapper(val move: Move) {
    fun beats(): Move {
        return when(move) {
            Move.ROCK -> Move.SCISSORS
            Move.SCISSORS -> Move.PAPER
            Move.PAPER -> Move.ROCK
        }
    }

    fun draws(): Move {
       return move
    }

    fun loses(): Move {
        return when(move) {
            Move.ROCK -> Move.PAPER
            Move.SCISSORS -> Move.ROCK
            Move.PAPER -> Move.SCISSORS
        }
    }

    fun score(): Int {
        return when(move) {
            Move.ROCK -> 1
            Move.PAPER -> 2
            Move.SCISSORS -> 3
        }
    }
}

fun mapMove(move: String): Move {
    return when(move) {
        "A", "X" -> Move.ROCK
        "B", "Y" -> Move.PAPER
        "C", "Z" -> Move.SCISSORS
        else -> throw RuntimeException("Unknown move: $move")
    }
}

fun mapMyNextMove(move: String): MoveNext {
    return when(move) {
        "X" -> MoveNext.LOSE
        "Y" -> MoveNext.DRAW
        "Z" -> MoveNext.WIN
        else -> throw RuntimeException("Unknown move: $move")
    }
}

fun main() {

    fun part1() {
        val testInput = readInput("Day02_test")
        var myScore = 0
        testInput.forEach { line ->
            val moves = line.split(" ")
            val opponentsMove = mapMove(moves[0])
            val myMove = mapMove(moves[1])
            val mappedMove = MoveMapper(myMove)

            if (mappedMove.beats() == opponentsMove) {
                myScore += mappedMove.score()
                myScore += 6
            } else if (mappedMove.draws() == opponentsMove) {
                myScore += mappedMove.score()
                myScore += 3
            } else  {
                myScore += mappedMove.score()
            }
        }
        println("Final Score pt1: $myScore")
    }

    fun part2() {
        val testInput = readInput("Day02_test")
        var myScore = 0
        testInput.forEach { line ->
            val moves = line.split(" ")
            val opponentsMove = MoveMapper(mapMove(moves[0]))

            when(mapMyNextMove(moves[1])) {
                MoveNext.DRAW -> {
                    myScore += MoveMapper(opponentsMove.draws()).score() + 3
                }
                MoveNext.LOSE -> {
                    myScore += MoveMapper(opponentsMove.beats()).score() + 0
                }
                MoveNext.WIN -> {
                    myScore += MoveMapper(opponentsMove.loses()).score() + 6
                }
            }


        }
        println("Final Score pt2: $myScore")

    }

    part1()
    part2()
}
