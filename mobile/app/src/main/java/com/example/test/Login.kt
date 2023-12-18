package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator

class Login : AppCompatActivity() {

    private lateinit var jsonTools: JSONTools
    private var backPressedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

            jsonTools.addUser(this, User(email, server, password))
            vibrate()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (backPressedCount == 1) {
            super.onBackPressed()
            return
        }

        backPressedCount++
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            backPressedCount = 0
        }, 2000)
    }

    private fun vibrate() {
        val vibrator = this.getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(100, 30))
    }
}

