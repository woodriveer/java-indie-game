package br.com.woodriver.api

import br.com.woodriver.api.response.CharactersResponse

interface RedGirlClient {
    fun login(user: String, password: String): Int
    fun loadCharacters(userId: Int): CharactersResponse
}