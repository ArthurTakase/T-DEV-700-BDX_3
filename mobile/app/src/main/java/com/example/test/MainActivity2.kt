package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import com.example.test.APIService

class MainActivity2 : AppCompatActivity() {

    private lateinit var jsonTools: JSONTools
    private val apiService = APIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        jsonTools = JSONTools()

        val serverEditText = findViewById<EditText>(R.id.edit_server_address)
        val emailEditText = findViewById<EditText>(R.id.edit_email_address)
        val passwordEditText = findViewById<EditText>(R.id.edit_password_address)
        val connectButton = findViewById<Button>(R.id.button_connect)

        val user = jsonTools.readUserJson(this)
        serverEditText.setText(user.server)
        emailEditText.setText(user.email)

        connectButton.setOnClickListener {
            val server = serverEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            Log.d("MainActivity2", "Server: $server, Email: $email, Password: $password")

            jsonTools.addUserEmail(this, email)
            jsonTools.addUserServer(this, server)

            apiService.getApi("https://pokeapi.co/api/v2/pokemon/lickilicky", onSuccess = { response ->
                Log.d("MainActivity2", "API Response: $response")
            }, onError = { error ->
                Log.e("MainActivity2", "API Error", error)
            })

            /*val customHeaders = mapOf("Authorization" to "Bearer your_token_here")
            apiService.callApi(
                urlString = "https://pokeapi.co/api/v2/pokemon/lickilicky",
                headers = customHeaders,
                onSuccess = { response ->
                    Log.d("MainActivity2", "API Response with headers: $response")
                },
                onError = { error ->
                    Log.e("MainActivity2", "API Error with headers", error)
                }
            ) avec arg*/

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        apiService.cancel() // Annuler les appels API lors de la destruction de l'activité
    }
}
