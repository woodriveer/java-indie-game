package br.com.woodriver.api.impl

import br.com.woodriver.api.RedGirlClient
import br.com.woodriver.api.request.LoginRequest
import br.com.woodriver.api.response.CharactersResponse
import br.com.woodriver.api.response.LoginResponse
import br.com.woodriver.extensions.logger
import br.com.woodriver.extensions.objectToJson
import br.com.woodriver.extensions.resultToObject
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody

class RedGirlClientImpl: RedGirlClient {

    private val apiUrl = "http://localhost:8080"
    private val client = OkHttpClient()
    val logger = logger<RedGirlClient>()

    override fun login(user: String, password: String): Int {
        logger.info("Starting to login $user calling api")
        val response = client.newCall(createRequest("$apiUrl/v1/login",
            RequestBody.create(
                MediaType.parse("application/json"),
                LoginRequest(user, password).objectToJson()
            ), POST
        )).execute()
        logger.info("Done requesting login for $user")
        return response.resultToObject<LoginResponse>().userId
    }

    override fun loadCharacters(userId: Int): CharactersResponse {
        val response = client.newCall(createRequest("$apiUrl/v1/characters/$userId", GET)).execute()
        return response.resultToObject()
    }

    private fun createRequest(url: String, method: String): Request =
        createRequest(url,null, method)

    private fun createRequest(url: String, body: RequestBody?, method: String): Request {
        val request = Request.Builder()
            .url(url)
        when(method) {
            POST -> request.post(body)
            else -> request.get()
        }
        return request.build()
    }

    companion object {
        const val POST = "post"
        const val GET = "get"
    }
}