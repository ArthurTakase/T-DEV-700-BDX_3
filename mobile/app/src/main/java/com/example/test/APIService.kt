package com.example.test

import android.util.Log
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class APIService {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getApi(
        urlString: String,
        method: String = "GET",
        headers: Map<String, String> = emptyMap(),
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        coroutineScope.launch {
            try {
                val url = URL(urlString)
                (url.openConnection() as? HttpURLConnection)?.run {
                    requestMethod = method
                    headers.forEach { (key, value) -> setRequestProperty(key, value) }

                    inputStream.bufferedReader().use {
                        val response = it.readText()
                        withContext(Dispatchers.Main) {
                            onSuccess(response)
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }

    fun postApi(
        urlString: String,
        postData: String,
        headers: Map<String, String> = emptyMap(),
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {
        coroutineScope.launch {
            try {
                val url = URL(urlString)
                (url.openConnection() as? HttpURLConnection)?.run {
                    requestMethod = "POST"
                    doOutput = true
                    headers.forEach { (key, value) -> setRequestProperty(key, value) }

                    outputStream.bufferedWriter().use { writer ->
                        writer.write(postData)
                        writer.flush()
                    }

                    inputStream.bufferedReader().use {
                        val response = it.readText()
                        withContext(Dispatchers.Main) {
                            onSuccess(response)
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }

    fun cancel() {
        coroutineScope.cancel()
    }
}
