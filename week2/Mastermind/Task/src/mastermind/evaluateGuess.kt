package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)
data class SingleLetterEvaluation(val rightPosition: Int, val wrongPosition: Int)


fun evaluateGuess(secret: String, guess: String): Evaluation {
    val secretMap = createMap(secret)
    val guessMap = createMap(guess)

    val evaluations: List<SingleLetterEvaluation> = guessMap.keys.map { letter ->
        val guessPositions = guessMap.getValue(letter)

        val secretPositions: MutableList<Int>? = secretMap[letter]

        if (secretPositions == null) {
            SingleLetterEvaluation(0, 0)
        } else {
            evaluateLetter(guessPositions, secretPositions)
        }
    }

    var result = Evaluation(0, 0)

    for (eval in evaluations) {
        result = Evaluation(result.rightPosition + eval.rightPosition,
                result.wrongPosition + eval.wrongPosition
        )
    }

    return result
}

fun createMap(s: String): Map<Char, MutableList<Int>> {
    val newMap = mutableMapOf<Char, MutableList<Int>>()

    s.forEachIndexed { index, char ->
        if (newMap.containsKey(char)) {
            newMap[char]!!.plusAssign(index)
        } else {
            newMap[char] = mutableListOf(index)
        }
    }

    return newMap.toMap()
}


fun evaluateLetter(guessPositions: MutableList<Int>, secretPositions: MutableList<Int>): SingleLetterEvaluation {
    var correctPosition = 0
    var incorrectPosition = 0

    val (matches, notMatched) = guessPositions.partition {
        it in secretPositions
    }

    matches.forEach { _ -> correctPosition += 1 }
    notMatched.forEach { _ ->
        // only count guesses as wrong position if there is a corresponding secret letter in a different position
        if (correctPosition + incorrectPosition < secretPositions.count()) {
            incorrectPosition += 1
        }
    }

    return SingleLetterEvaluation(correctPosition, incorrectPosition)
}
