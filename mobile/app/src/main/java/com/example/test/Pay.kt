package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView

class Pay : AppCompatActivity() {
    private var json: JSONTools = JSONTools()
    private var cash: Cash = Cash("", "", null)
    private var payContent: LinearLayout? = null
    private var payText: TextView? = null
    private var requests: Requests? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        payContent = findViewById(R.id.pay_content)
        payText = findViewById(R.id.pay_text)

        cash = json.readCashJson(this)
        Log.d("Requests", cash.accountName + " " + cash.accountNumber + " " + cash.amount)
        requests = Requests(this)
        requests?.payment(cash)

        val isOK = true // remplacer par la requête

        if (isOK) {
            json.emptyCartJson(this)
            json.emptyCashJson(this)

            payContent?.setBackgroundColor(resources.getColor(R.color.green))
            payText?.text = "Paiement accepté"

            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
            }, 3000)
        }
        else {
            payContent?.setBackgroundColor(resources.getColor(R.color.red))
            payText?.text = "Paiement refusé"
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
            }, 3000)
        }
    }
}