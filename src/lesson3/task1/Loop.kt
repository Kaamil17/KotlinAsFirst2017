@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import lesson5.task1.bestHighJump
import java.lang.StrictMath.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = abs(n)
    do {
        number /= 10
        count++
    } while (number > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1
    for (i in 2..n) {
        val x = fib1 + fib2
        fib1 = fib2
        fib2 = x
    }
    return fib1
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = (m * n) / gcd(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n / 2)
        if (n % i == 0)
            return i
    return n
}

/**
ITS MY PRIVATE FUNCTION
 */
fun gcd(m: Int, n: Int): Int {
    var n1 = n
    var m1 = m
    while (n1 != m1) {
        if (n1 > m1)
            n1 -= m1
        else
            m1 -= n1
    }
    return m1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *'f'.toInt() 57.toChar() '0'..'1'
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean =
        Math.ceil(Math.sqrt(m.toDouble())) <= Math.floor(Math.sqrt(n.toDouble()))

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var newX = x % (2 * PI)
    var sinus = 0.0
    var n = 1.0
    var sign = 1
    while (pow(newX, n) / factorial(n.toInt()) > eps) {
        sinus += sign * pow(newX, n) / factorial(n.toInt())
        n += 2.0
        sign *= -1
    }
    return sinus
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val newX = x % (2 * PI)
    var cosinus = 0.0
    var n = 0.0
    var sign = 1
    while (pow(newX, n) / factorial(n.toInt()) > eps) {
        cosinus += sign * pow(newX, n) / factorial(n.toInt())
        n += 2.0
        sign *= -1
    }
    return cosinus
}

/**
 * Средняя
 * sqrt(7)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var revertNum = 0
    var auxNum = n
    do {
        revertNum = revertNum * 10 + auxNum % 10
        auxNum /= 10
    } while (auxNum > 0)
    return revertNum
}


/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.22q99
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    val number1 = number % 10
    if (number < 10)
        return false
    do {
        number /= 10
        if (number1 != number % 10)
            return true
    } while (number > 9)
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var firstSequence = 1
    var secondSequence = 1
    var l = n
    while (l > 0) {
        secondSequence = firstSequence * firstSequence
        l -= digitNumber(secondSequence)
        firstSequence++
    }
    return secondSequence / pow(10.0, (abs(l) * 1).toDouble()).toInt() % 10
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */

fun fibSequenceDigit(n: Int): Int {
    var sequenceDigit = 1
    var seqeunceDigit2 = 1
    var length = n
    while (length > 0) {
        seqeunceDigit2 = fib(sequenceDigit)
        length -= digitNumber(seqeunceDigit2)
        sequenceDigit++
    }
    return seqeunceDigit2 / pow(10.0, (abs(length) * 1).toDouble()).toInt() % 10

}