package com.example.kotlin

import kotlin.math.PI
import kotlin.random.Random

fun main() {
    exerciseOne()
    exerciseTwo()
    exerciseThree()
    exerciseFour()
    exerciseFive()
    exerciseSix()
    exerciseSeven()
    exerciseEight()
    exerciseNine()
    exerciseTen()
    exerciseEleven()
    exerciseTwelve()
}


/**
 * Complete the code to make the program print
 * "Mary is 20 years old" to standard output.
 */
fun exerciseOne() {
    val name = "Mary"
    val age = 20
    println("$name is $age years old")
}

/**
 * Explicitly declare the correct type for each variable.
 */
fun exerciseTwo() {
    val a: Int = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'
    println("a: $a (Int)")
    println("b: $b (String)")
    println("c: $c (Double)")
    println("d: $d (Long)")
    println("e: $e (Boolean)")
    println("f: $f (Char)")
}

/**
 * You have a list of “green” numbers and a list of “red” numbers.
 * Complete the code to print how many numbers there are in total.
 */
fun exerciseThree() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)

    val totalCount = greenNumbers.size + redNumbers.size
    println("Total number of elements: $totalCount")
}

/**
 * You have a set of protocols supported by your server. A user requests to use a particular protocol.
 * Complete the program to check whether the requested protocol
 * is supported or not (isSupported must be a Boolean value).
 */
fun exerciseFour() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * Define a map that relates integer numbers from 1 to 3 to their
 * corresponding spelling. Use this map to spell the given number.
 */
fun exerciseFive() {
    val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")
}

/**
 * Create a simple game where you win if throwing two dice results in the same number.
 * Use if to print You win :) if the dice match or You lose :( otherwise
 */
fun exerciseSix() {
    val firstResult = Random.nextInt(6)
    val secondResult = Random.nextInt(6)

    println("First dice: $firstResult")
    println("Second dice: $secondResult")

    if (firstResult == secondResult) {
        println("You win :)")
    } else {
        println("You lose :(")
    }
}

/**
 * Using a when expression, update the following program so that it prints the corresponding
 * actions when you input the names of game console buttons.
 * Button Action
 * A Yes
 * B No
 * X Menu
 * Y Nothing
 * Other There is no such button
 *
 */
fun exerciseSeven() {
    val button = "A"
    println(
        when (button) {
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "Nothing"
            else -> "There is no such button"
        }
    )
}

/**
 * You have a program that counts pizza slices until there’s a whole pizza with 8 slices. Refactor this program in two ways:
 * Use a while loop.
 * Use a do-while loop.
 */
fun exerciseEight() {
    var pizzaSlices = 0

    while (pizzaSlices < 7) {
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
    }

    pizzaSlices++
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
}

/**
 * Write a program that simulates the Fizz buzz game. Your task is to print numbers
 * from 1 to 100 incrementally, replacing any number divisible by three with the word
 * "fizz", and any number divisible by five with the word "buzz". Any number divisible
 * by both 3 and 5 must be replaced with the word "fizzbuzz".
 */
fun exerciseNine() {
    for (i in 1..100) {
        println(
            when {
                i % 3 == 0 && i % 5 == 0 -> "fizzbuzz"
                i % 3 == 0 -> "fizz"
                i % 5 == 0 -> "buzz"
                else -> i.toString()
            }
        )
    }
}

/**
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 */
fun exerciseTen() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")

    for (word in words) {
        if (word.startsWith("l")) {
            println(word)
        }
    }
}

/**
 * Write a function called circleArea that takes the radius of a circle in
 * integer format as a parameter and outputs the area of that circle.
 */
fun exerciseEleven() {
    println(circleArea(2))
}

fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

/**
 * Rewrite the circleArea function from the previous exercise as a single-expression function.
 */
fun exerciseTwelve() {
    println(circleAreaSingleExpression(2))
}

fun circleAreaSingleExpression(radius: Int): Double = PI * radius * radius


