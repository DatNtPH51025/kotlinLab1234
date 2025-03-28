package com.poly.lab1234_ph51025

fun main() {
    //bai 1
    print("Nguyễn Tiến Đạt - PH51025\n")

    println("Quanh năm buôn bán ở mom sông")
    println("Nuôi đủ năm con với một chồng")
    println("\tlặn lội thân cờ khi quãng vắng")
    println("\teo sèo mặt nước buổi đò đồng")
    println("Một duyên hai nợ âu đành phận")
    println("Năm nắng mười mưa há chẳng công")
    println("\tCha mẹ thói đời \"ăn ở bạc\"")
    println("\tCó chồng hờ hững cũng như không")
    // bai2
    print("Nhập a: ")
    val a = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    print("Nhập b: ")
    val b = readlnOrNull()?.toDoubleOrNull() ?: 0.0

    println("Tổng: ${a + b}")
    println("Hiệu: ${a - b}")
    println("Tích: ${a * b}")
    println(if (b != 0.0) "Thương: ${a / b}"
    else "Không thể chia cho 0")
}