package chap02

import java.util.*

/**
 * @Author Heli
 */
abstract class Translator {
    abstract fun translate(expression: String): String

    private val operatorPriorities = hashMapOf(
        '+' to 2,
        '-' to 2,
        '*' to 3,
        '/' to 3,
    )
    protected val Char.priority: Int
        get() = operatorPriorities[this] ?: -1

    protected val Char.isOperator: Boolean
        get() = operatorPriorities.containsKey(this)

    protected val Char.isOpenBracket: Boolean
        get() = this == '('

    protected val Char.isNotOpenBracket: Boolean
        get() = !isOpenBracket

    protected val Char.isCloseBracket: Boolean
        get() = this == ')'
}

class InfixToPostfixTranslator : Translator() {

    private val stack = Stack<Char>()

    override fun translate(expression: String) = buildString {
        expression.forEach {
            if (it.isOperator) {
                while (stack.isNotEmpty() && stack.peek().priority >= it.priority) {
                    append(stack.pop())
                }
                stack.push(it)
            } else if (it.isOpenBracket) {
                stack.push(it)
            } else if (it.isCloseBracket) {
                while (stack.isNotEmpty() && stack.peek().isNotOpenBracket) {
                    append(stack.pop())
                }
                stack.pop()
            } else {
                append(it)
            }
        }
        while (stack.isNotEmpty()) {
            append(stack.pop())
        }
    }
}
