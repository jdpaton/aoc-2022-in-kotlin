fun main() {

    fun part1() {
        val testInput = readInput("Day01_test")

        var elfIndex = 1
        var currentTotal = 0
        val elfMap = mutableMapOf<Int, Int>()
        testInput.forEach { line ->
            if (line.isNotEmpty()) {
                currentTotal += line.toInt()
            } else {
                elfMap[elfIndex] = currentTotal
                currentTotal = 0
                elfIndex += 1
            }
        }
        val biggestElfIdx = elfMap.maxBy { it.value }
        println(biggestElfIdx)
    }

    fun part2() {
        val testInput = readInput("Day01_test")

        var elfIndex = 1
        var currentTotal = 0
        val elfMap = mutableMapOf<Int, Int>()
        testInput.forEach { line ->
            if (line.isNotEmpty()) {
                currentTotal += line.toInt()
            } else {
                elfMap[elfIndex] = currentTotal
                currentTotal = 0
                elfIndex += 1
            }
        }

        val topThree = mutableSetOf<Pair<Int,Int>>()
        for(i in 1..3) {
            val elf = elfMap.maxBy { it.value }
            topThree.add(elf.toPair())
            elfMap.remove(elf.key)
        }
        println(topThree.sumOf { it.second })
    }

    part1()
    part2()
}
