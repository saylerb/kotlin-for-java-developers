package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var totalCorrect = 0
    var totalIncorrect = 0

    for ((index, secretLetter) in secret.withIndex()) {
        val guessLetter = guess[index]

        if (guessLetter == secretLetter) {
            totalCorrect++
        } else if (secretLetter in guess) {
            totalIncorrect++
        }

    }
    return Evaluation(totalCorrect, totalIncorrect)

}
