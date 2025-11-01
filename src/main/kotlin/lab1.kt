fun main() {
    while (true) {
        println("Выбери задачу:")
        println("1 - Сжать строку")
        println("2 - Посчитать символы")
        println("3 - В двоичную")
        println("4 - Калькулятор")
        println("5 - Найти степень")
        println("6 - Нечетное число")
        println("0 - Выход")

        when (readln()) {
            "1" -> {
                println("Введи строку:")
                val inputString = readln()
                if (inputString.isNotEmpty()) println(compressString(inputString))
            }
            "2" -> {
                println("Введи строку:")
                val inputString = readln()
                val charCountMap = mutableMapOf<Char, Int>()
                inputString.forEach { char ->
                    charCountMap[char] = charCountMap.getOrDefault(char, 0) + 1
                }
                charCountMap.entries.sortedBy { it.key }.forEach {
                    println("${it.key} - ${it.value}")
                }
            }
            "3" -> {
                println("Введи число:")
                val number = readln().toIntOrNull() ?: 0
                if (number > 0) println(number.toString(2))
            }
            "4" -> {
                println("Введи: число1 число2 операция")
                val inputParts = readln().split(" ")
                if (inputParts.size == 3) {
                    val firstNumber = inputParts[0].toDoubleOrNull()
                    val secondNumber = inputParts[1].toDoubleOrNull()
                    val operation = inputParts[2]

                    if (firstNumber != null && secondNumber != null) {
                        val result = when (operation) {
                            "+" -> firstNumber + secondNumber
                            "-" -> firstNumber - secondNumber
                            "*" -> firstNumber * secondNumber
                            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Делить на ноль нельзя"
                            else -> "Неизвестная операция"
                        }
                        println(result)
                    }
                }
            }
            "5" -> {
                println("Введи n и x:")
                val inputParts = readln().split(" ")
                if (inputParts.size == 2) {
                    val targetNumber = inputParts[0].toIntOrNull()
                    val baseNumber = inputParts[1].toIntOrNull()

                    if (targetNumber != null && baseNumber != null && targetNumber > 0 && baseNumber > 1) {
                        var currentPower = 1
                        var exponent = 0

                        while (currentPower < targetNumber) {
                            currentPower *= baseNumber
                            exponent++
                        }

                        println(if (currentPower == targetNumber) exponent else "Нет такого показателя")
                    }
                }
            }
            "6" -> {
                println("Введи первую цифру:")
                val firstDigit = readln().toIntOrNull()
                println("Введи вторую цифру:")
                val secondDigit = readln().toIntOrNull()

                if (firstDigit != null && secondDigit != null && firstDigit in 0..9 && secondDigit in 0..9 && firstDigit != secondDigit) {
                    val firstCombination = firstDigit * 10 + secondDigit
                    val secondCombination = secondDigit * 10 + firstDigit

                    when {
                        firstCombination % 2 != 0 -> println(firstCombination)
                        secondCombination % 2 != 0 -> println(secondCombination)
                        else -> println("Нечетное число нельзя составить")
                    }
                }
            }
            "0" -> return
        }
        println()
    }
}

fun compressString(input: String): String {
    if (input.isEmpty()) return ""

    val compressedResult = StringBuilder()
    var currentChar = input[0]
    var count = 1

    for (i in 1 until input.length) {
        if (input[i] == currentChar) {
            count++
        } else {
            compressedResult.append(currentChar)
            if (count > 1) compressedResult.append(count)
            currentChar = input[i]
            count = 1
        }
    }

    compressedResult.append(currentChar)
    if (count > 1) compressedResult.append(count)

    return compressedResult.toString()
}