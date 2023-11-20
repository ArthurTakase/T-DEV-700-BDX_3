package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<LinearLayout>(R.id.main_layout)

        val products = arrayOf(
            Product("Arthur", 10, "Le plus beau", R.drawable.arthur),
            Product("Corentin", 100, "Presque le plus beau", R.drawable.corentin),
            Product("Theau", 30, "Occasion, très peu utilisé", R.drawable.theau),
            Product("Guillaume", 50, "Chat GPT", R.drawable.guillaume),
            Product("Geoffrey", 42, "Une grosse merde", R.drawable.geoffrey)
        )

        for (product in products) {
            val productItem = LayoutInflater.from(this).inflate(R.layout.product_card, mainLayout, false)
            productItem.findViewById<TextView>(R.id.product_name).text = product.name
            productItem.findViewById<TextView>(R.id.product_price).text = "$${product.price} - ADD TO CART"
            productItem.findViewById<TextView>(R.id.product_description).text = product.description
            productItem.findViewById<ImageView>(R.id.product_image).setImageResource(product.image)

            mainLayout.addView(productItem)
        }
    }
}