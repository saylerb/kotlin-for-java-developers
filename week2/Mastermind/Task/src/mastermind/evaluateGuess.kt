package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val correctLetters = mutableListOf<Char>()
    val incorrectLetters = mutableListOf<Char>()

    var secretWithoutPreviousCorrectLetters = secret

    for ((index, guessLetter) in guess.withIndex()) {
        val secretLetter = secret[index]
        val repeatsOfLetterInSecret = secret.count { it == guessLetter }
        val guessLetterCountedAsIncorrect = incorrectLetters.count { it == guessLetter }
        val guessLetterCountedAsCorrect = correctLetters.count { it == guessLetter }

        if (guessLetter == secretLetter) {
            correctLetters.add(guessLetter)
            secretWithoutPreviousCorrectLetters =
                secretWithoutPreviousCorrectLetters.replaceFirst(guessLetter, '*')

            if (guessLetterCountedAsIncorrect == repeatsOfLetterInSecret) {
                incorrectLetters.remove(guessLetter)
            }

        } else if ((guessLetter in secretWithoutPreviousCorrectLetters)) {
            if (guessLetterCountedAsIncorrect + guessLetterCountedAsCorrect != repeatsOfLetterInSecret) {
                incorrectLetters.add(guessLetter)
            }
        }

    }
    return Evaluation(correctLetters.size, incorrectLetters.size)

}


