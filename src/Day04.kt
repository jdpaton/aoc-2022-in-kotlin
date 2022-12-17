fun main() {

    fun part1() {
        val testInput = readInput("Day04_test")
        var fullOverlapCount = 0
        val rangeIter = mapToRanges(testInput).iterator()
        while(rangeIter.hasNext()) {
            val rangePair = rangeIter.next()
            val allInOne = rangePair.first.all { t-> rangePair.second.contains(t) }
            val allInTwo = rangePair.second.all { t-> rangePair.first.contains(t) }

            if(allInOne || allInTwo) {
                fullOverlapCount += 1
            }
        }
        println("Pt1 count: $fullOverlapCount")
    }

    fun part2() {
        val testInput = readInput("Day04_test")
        var partialOverlapCount = 0
        val rangeIter = mapToRanges(testInput).iterator()
        while(rangeIter.hasNext()) {
            val rangePair = rangeIter.next()
            val allInOne = rangePair.first.any { t-> rangePair.second.contains(t) }
            val allInTwo = rangePair.first.any { t-> rangePair.second.contains(t) }

            if(allInOne || allInTwo) {
                partialOverlapCount += 1
            }
        }
        println("Pt2 count: $partialOverlapCount")
    }
    part1()
    part2()
}

fun mapToRanges(input: List<String>) = sequence {
    input.forEach { line ->
        val pair = line.split(",")
        val ranges = pair.map { it.split("-") }
        check(ranges.size == 2)
        val rangeOne = (ranges[0][0].toInt()..ranges[0][1].toInt()).toList()
        val rangeTwo = (ranges[1][0].toInt()..ranges[1][1].toInt()).toList()
        yield(Pair(rangeOne,rangeTwo))
    }
}
