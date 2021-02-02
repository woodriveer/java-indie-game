package br.com.woodriver.extensions

import br.com.woodriver.utils.Logger


inline fun <reified T> logger(): Logger {
    val className = T::class.java.toString().split('.').last()
    return Logger(String.format("%-15s", className).substring(0, 15))
}