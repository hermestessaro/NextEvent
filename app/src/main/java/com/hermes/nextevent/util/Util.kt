package com.hermes.nextevent.util

fun String.isValidEmail(): Boolean {
    val regex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return regex.matches(this) && this.isNotEmpty()
}