package mastermind

import org.junit.Assert

internal fun testEvaluation(first: String, second: String, positions: Int, letters: Int) {
    val evaluation = Evaluation(positions, letters)
    testEvaluationOneWay(first, second, evaluation)
    testEvaluationOneWay(second, first, evaluation)
}

internal fun testEvaluationOneWay(secret: String, guess: String, expected: Evaluation) {
    val evaluation = evaluateGuess(secret, guess)
    Assert.assertEquals("Wrong evaluation for secret=$secret, guess=$guess",
            expected, evaluation)
}
