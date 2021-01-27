package br.com.woodriver.api.request

data class LoginRequest(
    val username: String = "",
    val password: String = ""
)