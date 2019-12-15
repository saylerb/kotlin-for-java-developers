package mastermind

import org.junit.Assert.assertEquals
import org.junit.Test

class EvaluateGuessKtTest {

    @Test
    fun `should count correct positions when guess and secret match`() {
        val guessList = mutableListOf(0, 1, 2)
        val secretList = mutableListOf(0, 1, 2)

        val result: SingleLetterEvaluation = evaluateLetter(guessList, secretList)

        val expected = SingleLetterEvaluation(3, 0)

        assertEquals(expected, result)
    }

    @Test
    fun `should count a guess position as incorrect when secret has the same number of position but positions do not match`() {
        val guessList = mutableListOf(0, 1, 5)
        val secretList = mutableListOf(0, 1, 2)

        val result: SingleLetterEvaluation = evaluateLetter(guessList, secretList)

        val expected = SingleLetterEvaluation(2, 1)

        assertEquals(expected, result)
    }


    @Test
    fun `should count correct positions when secret contains more occurrences than guess`() {
        val guessList = mutableListOf(1)
        val secretList = mutableListOf(0, 1, 3)

        val result: SingleLetterEvaluation = evaluateLetter(guessList, secretList)

        val expected = SingleLetterEvaluation(1, 0)

        assertEquals(expected, result)
    }


    @Test
    fun `should not count a position as incorrect if secret has fewer occurrences`() {
        val guessList = mutableListOf(1, 3)
        val secretList = mutableListOf(3)

        val result: SingleLetterEvaluation = evaluateLetter(guessList, secretList)

        val expected = SingleLetterEvaluation(1, 0)

        assertEquals(expected, result)
    }


}
