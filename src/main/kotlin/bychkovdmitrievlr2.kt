import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n=== ВЫБЕРИТЕ ЗАДАЧУ ===")
        println("1 - Разные цифры в массиве")
        println("2 - Симметрия матрицы")
        println("3 - Шифрование")
        println("4 - Пересечение массивов")
        println("5 - Группы слов")
        println("0 - Выход")
        print("Ваш выбор: ")

        when (scanner.nextInt()) {
            1 -> task1(scanner)
            2 -> task2(scanner)
            3 -> task3(scanner)
            4 -> task4(scanner)
            5 -> task5(scanner)
            0 -> return
        }
        scanner.nextLine()
    }
}

fun task1(scanner: Scanner) {
    println("\nЗадача 1: Разные цифры в массиве")

    print("Строк: ")
    val rows = scanner.nextInt()
    print("Столбцов: ")
    val cols = scanner.nextInt()

    val matrix = Array(rows) { IntArray(cols) }
    println("Введите $rows строк по $cols столбца(ввод 3-х значных чисел):")

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            matrix[i][j] = scanner.nextInt()
        }
    }

    val digits = mutableSetOf<Char>()
    for (row in matrix) {
        for (num in row) {
            for (digit in num.toString()) {
                digits.add(digit)
            }
        }
    }

    println("Разных цифр: ${digits.size}")
}

fun task2(scanner: Scanner) {
    println("\nЗадача 2: Симметрия матрицы")
    scanner.nextLine()

    val matrix = Array(5) { IntArray(5) }
    println("Введите 5 строк по 5 чисел:")

    for (i in 0 until 5) {
        val numbers = scanner.nextLine().split(" ").map { it.toInt() }
        for (j in 0 until 5) {
            matrix[i][j] = numbers[j]
        }
    }

    var symmetric = true
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            if (matrix[i][j] != matrix[j][i]) {
                symmetric = false
            }
        }
    }

    println(if (symmetric) "Симметрична" else "Не симметрична")
}

fun task3(scanner: Scanner) {
    println("\nЗадача 3: Шифрование")
    scanner.nextLine()

    // Русский алфавит (33 буквы)
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"

    // Коды для каждой буквы (от 1 до 33)
    val codes = listOf(21,13,4,20,22,1,25,12,24,14,2,28,9,23,3,29,6,16,15,11,26,5,30,27,8,18,10,33,31,32,19,7,17)

    // Создаем маппинг: буква -> код и код -> буква
    val charToCode = mutableMapOf<Char, Int>()
    val codeToChar = mutableMapOf<Int, Char>()

    for (i in alphabet.indices) {
        charToCode[alphabet[i]] = codes[i]
        codeToChar[codes[i]] = alphabet[i]
    }

    print("Шифровать(1) или расшифровать(2): ")
    val action = scanner.nextInt()
    scanner.nextLine()

    print("Ключ: ")
    val key = scanner.nextLine().uppercase().replace(" ", "")
    print("Текст: ")
    val text = scanner.nextLine().uppercase().replace(" ", "")

    var result = ""

    for (i in text.indices) {
        val textChar = text[i]
        val keyChar = key[i % key.length]

        val textCode = charToCode[textChar] ?: continue
        val keyCode = charToCode[keyChar] ?: continue

        if (action == 1) {
            // Шифрование: (код_текста + код_ключа - 2) % 33 + 2
            val newCode = (textCode + keyCode - 2) % 33+2
            result += codeToChar[newCode] ?: '?'
        } else {
            // Дешифрование: (код_текста - код_ключа + 33 ) % 33 + 1
            val newCode = (textCode - keyCode + 33) % 33 + 1
            result += codeToChar[newCode] ?: '?'
        }
    }

    println("Результат: $result")
}

fun task4(scanner: Scanner) {
    println("\nЗадача 4: Пересечение массивов")
    scanner.nextLine()

    print("Первый массив: ")
    val arr1 = scanner.nextLine().split(" ").map { it.toInt() }
    print("Второй массив: ")
    val arr2 = scanner.nextLine().split(" ").map { it.toInt() }

    val result = mutableListOf<Int>()
    val list2 = arr2.toMutableList()

    for (num in arr1) {
        if (num in list2) {
            result.add(num)
            list2.remove(num)
        }
    }

    result.sort()
    println("Пересечение: ${result.joinToString()}")
}

fun task5(scanner: Scanner) {
    println("\nЗадача 5: Группы слов")
    scanner.nextLine() // очистка буфера

    print("Введите слова через пробел: ")
    val input = scanner.nextLine()
    val words = input.split(" ").map { it.trim() }.filter { it.isNotEmpty() }

    val groups = mutableMapOf<String, MutableList<String>>()

    for (word in words) {
        val sorted = word.toCharArray().sorted().joinToString("")
        if (!groups.containsKey(sorted)) {
            groups[sorted] = mutableListOf()
        }
        groups[sorted]!!.add(word)
    }

    println("\nГруппы слов:")
    for (group in groups.values) {
        if (group.size > 1) {
            println(group.joinToString(", "))
        }
    }

    // Вывод одиночных слов
    val singleWords = groups.values.filter { it.size == 1 }.flatten()
    if (singleWords.isNotEmpty()) {
        println("Одиночные слова: ${singleWords.joinToString(", ")}")
    }
}