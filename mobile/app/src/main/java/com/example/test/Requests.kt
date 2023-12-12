package com.example.test

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext

class Requests(context: Context) {
    private val api = API()
    private val json = JSONTools()
    private var infos: User? = null
    private var url: String = ""

    init {
        infos = json.readUserJson(context)
        url = infos!!.server
    }

    fun getUsers() {
        api.get("$url/users",
            onSuccess = { response ->
                println(response)
            },
            onError = { error ->
                println(error)
            }
        )
    }

    fun postUser(email: String, name: String, password: String) {
        val postData = """
            {
                "email": $email,
                "name": $name,
                "password": $password
            }
        """.trimIndent()

        api.post("$url/users", postData,
            onSuccess = { response ->
                println(response)
            },
            onError = { error ->
                println(error)
            }
        )
    }

    fun payment(cash: Cash) {
        val postData = """
            {
                "amount": ${cash.amount},
            }
        """.trimIndent()

        api.post("$url/purchase/${cash.accountNumber}", postData,
            onSuccess = { response ->
                Log.d("Requests", "SUCCESS" + response)
            },
            onError = { error ->
                Log.d("Requests", "ERROR" + error.toString())
            }
        )
    }
}