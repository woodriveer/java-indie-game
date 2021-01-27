package br.com.woodriver.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CharactersResponse(
    val characters: List<Character> = arrayListOf()
) {
    data class Character(
        val name: String = "",
        val race: String = "",
        val clazz: String = "",
        val level: Int = 0,
        val gender: String = ""
    )
}