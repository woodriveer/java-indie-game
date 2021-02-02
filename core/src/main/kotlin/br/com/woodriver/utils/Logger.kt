package br.com.woodriver.utils

import java.time.LocalDateTime

class Logger(private val className: String) {

    fun error(message: String) {
        println("$RED$className|${LocalDateTime.now()}| $message$RESET")
    }

    fun warning(message: String){
        println("$YELLOW$className|${LocalDateTime.now()}| $message$RESET")
    }

    fun info(message: String){
        println("$className|${LocalDateTime.now()}| $message$RESET")
    }

    companion object {
        const val RED = "\u001B[31m"
        const val RESET = "\u001B[0m"
        const val YELLOW = "\u001B[33m"
    }
}