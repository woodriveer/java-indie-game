package br.com.woodriver.extensions

import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.Response

fun Any.objectToJson(): String = ObjectMapper().writeValueAsString(this)

inline fun <reified T> Response.resultToObject(): T {
    return ObjectMapper().readValue(this.body().byteStream(), T::class.java)
}