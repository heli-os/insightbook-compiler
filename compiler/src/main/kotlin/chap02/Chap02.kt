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
    private val brackets = setOf('(', ')')

    protected val Char.priority: Int
        get() = operatorPriorities[this] ?: -1

    protected val Char.isOperator: Boolean
        get() = operatorPriorities.containsKey(this)

    protected val Char.isBracket: Boolean
        get() = brackets.contains(this)
}

class InfixToPostfixTranslator : Translator() {

    private val stack = Stack<Char>()

//    init {
//        stack.push('(')
//    }

    override fun translate(expression: String): String {
        val sb = StringBuilder()
        expression.forEach {
            if (it.isOperator) {
                while (stack.isNotEmpty() && stack.peek().priority >= it.priority) {
                    sb.append(stack.pop())
                }
                stack.push(it)
            } else if (it == '(') {
                stack.push(it)
            } else if (it == ')') {
                while (true) {
                    val top = stack.pop()
                    if (top == '(') break
                    sb.append(top)
                }
            } else {
                sb.append(it)
            }
        }
        while (stack.isNotEmpty()) {
            sb.append(stack.pop())
        }
        return sb.toString()
    }
}
