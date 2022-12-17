fun main() {

    fun part1(reversed: Boolean = false) {
        val testInput = readInput("Day05_test")

        val stacks = mapStacks(testInput)
        testInput.filter { it.startsWith("move") }.forEach {
            val moveSpl = it.split(" ")
            val numberOfCratesToMove = moveSpl[1].toInt()
            check(moveSpl[2] == "from")
            val fromColumn = moveSpl[3].toInt()
            check(moveSpl[4] == "to")
            val toColumn = moveSpl[5].toInt()

            val stacksMoving: MutableList<String?> = mutableListOf()
            for(i in 1..numberOfCratesToMove){
                stacksMoving.add(
                    stacks[fromColumn]
                        ?.removeLast()
                        .toString()
                )
            }

            if(reversed) {
                stacksMoving
                    .reversed().forEach { crate -> stacks[toColumn]
                        ?.addLast(crate.toString()) }
            } else {
                stacksMoving
                    .forEach { crate -> stacks[toColumn]
                        ?.addLast(crate.toString()) }
            }

        }

        val output = stacks
            .map { stack -> stack.value.last() }
            .joinToString("")

        println(output)
    }

    fun part2() { part1(reversed = true)}
    part1()
    part2()
}

fun mapStacks(input: List<String>): Map<Int,ArrayDeque<String>>{
    val stacks: MutableMap<Int,ArrayDeque<String>> = mutableMapOf()

    input.filter{it.startsWith('[')}.map {
        val lineFormatted = it.replace("    ", " [] ")
        val iter = lineFormatted.iterator()
        var column = 1

        while(iter.hasNext()) {
            val char = iter.next().toString()
            if( char == "[") {
                if(iter.hasNext()) {
                    if(stacks[column] == null) {
                        stacks[column] = ArrayDeque()
                    }
                    val nextChar = iter.next().toString()
                    if(nextChar != "]") {
                        stacks[column]?.addFirst(nextChar)
                    } else {
                        column += 1
                    }
                }
            } else if(char == "]") {
                column += 1
            }
        }
    }
    return stacks
}
