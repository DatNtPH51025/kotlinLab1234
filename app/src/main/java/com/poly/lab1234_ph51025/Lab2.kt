package com.poly.lab1234_ph51025

fun main() {
    // Bài 1
    print("Nhập a: ")
    val a = readlnOrNull()?.toDoubleOrNull() ?: 0.0
    print("Nhập b: ")
    val b = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    when {
        a == 0.0 && b == 0.0 -> println("Phương trình vô số nghiệm")
        a == 0.0 -> println("Phương trình vô nghiệm")
        else -> println("Nghiệm x = ${-b / a}")
    }

    // Bài 2
    print("Nhập tháng: ")
    val month = readlnOrNull()?.toIntOrNull() ?: -1

    println(
        when (month) {
            in 1..3 -> "Tháng $month thuộc quý 1"
            in 4..6 -> "Tháng $month thuộc quý 2"
            in 7..9 -> "Tháng $month thuộc quý 3"
            in 10..12 -> "Tháng $month thuộc quý 4"
            else -> "Tháng $month không hợp lệ"
        }
    )

    // Bài 3
    println("Chương trình kiểm tra năm nhuận:")
    do {
        print("Nhập năm: ")
        val year = readlnOrNull()?.toIntOrNull() ?: -1

        if (year < 0) {
            println("Năm không hợp lệ, vui lòng nhập lại.")
            continue
        }

        println(
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                "Năm $year là năm nhuận"
            else
                "Năm $year không phải là năm nhuận"
        )

        print("Tiếp tục? (c/k): ")
    } while (readlnOrNull()?.lowercase() != "k")
    println("Kết thúc chương trình")
}
