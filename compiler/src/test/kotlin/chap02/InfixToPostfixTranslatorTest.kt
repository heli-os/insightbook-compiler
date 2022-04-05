package chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @Author Heli
 */
internal class InfixToPostfixTranslatorTest {

    private lateinit var translator: Translator

    @BeforeEach
    private fun beforeEach() {
        translator = InfixToPostfixTranslator()
    }

    @Test
    fun `중위 표기법의 식을 읽고 후위 표기법으로 변환할 수 있다 (1)`() {
        val (inputExpression, expectedExpression) = "a+b*c" to "abc*+"
        val actual = translator.translate(inputExpression)
        assertEquals(expectedExpression, actual)
    }

    @Test
    fun `중위 표기법의 식을 읽고 후위 표기법으로 변환할 수 있다 (2)`() {
        val (inputExpression, expectedExpression) = "(a+b)*c+d" to "ab+c*d+"
        val actual = translator.translate(inputExpression)
        assertEquals(expectedExpression, actual)
    }

    @Test
    fun `중위 표기법의 식을 읽고 후위 표기법으로 변환할 수 있다 (3)`() {
        val (inputExpression, expectedExpression) = "a+b*c*(d+e)" to "abc*de+*+"
        val actual = translator.translate(inputExpression)
        assertEquals(expectedExpression, actual)
    }

    @Test
    fun `중위 표기법의 식을 읽고 후위 표기법으로 변환할 수 있다 (4)`() {
        val (inputExpression, expectedExpression) = "a*b*c+d+e" to "ab*c*d+e+"
        val actual = translator.translate(inputExpression)
        assertEquals(expectedExpression, actual)
    }
}
