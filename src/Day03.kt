fun main() {

    fun part1() {
        val testInput = readInput("Day03_test")
        var sum = 0

        testInput.forEach { line ->
            val compartmentOne = line.substring(0, (line.length/2)).toCharArray()
            val compartmentTwo = line.substring(line.length/2, line.length).toCharArray()
            check(compartmentOne.size == compartmentTwo.size)

            val commonChars = compartmentOne.filterIndexed{ i: Int, c: Char -> compartmentTwo.contains(c) }
            val commonCharsTwo = compartmentTwo.filterIndexed{ i: Int, c: Char -> compartmentOne.contains(c) }

            val commonSet = (commonChars + commonCharsTwo).toSet()
            commonSet.forEach {
                sum += getScore(it)
            }

        }
        println("Pt1 final score: $sum")
    }

    fun part2() {
        val testInput = readInput("Day03_test")
        var groupIncr = 0
        val groupMap = mutableMapOf<Int,MutableList<String>>()
        var currGroup = 0
        var totalScore = 0
        testInput.forEachIndexed { idx,line ->
            if(!groupMap.containsKey(groupIncr)) {
                groupMap[groupIncr] = mutableListOf()
            }
            groupMap[groupIncr]?.add(line)
            currGroup += 1
            if(currGroup >= 3) {
                groupIncr += 1
                currGroup = 0
            }
        }

       groupMap.forEach {
           val currentInterest = it.value[0].toCharArray()
               .intersect(it.value[1].toCharArray().toList().toSet())
               .intersect(it.value[2].toCharArray().toList().toSet())

          check(currentInterest.size == 1)

           totalScore += getScore(currentInterest.first())
       }
        println("Pt2 final score: $totalScore")
    }

    part1()
    part2()
}

fun getScore(char: Char): Int {
    var currentScore = 1
    val scoreMap = mutableMapOf<Char, Int>()
    for (char in 'a'..'z') {
        scoreMap[char] = currentScore
        currentScore += 1
    }
    for (char in 'A'..'Z') {
        scoreMap[char] = currentScore
        currentScore += 1
    }
    return scoreMap.getOrDefault(char,0)
}
