package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Store())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.store -> { replaceFragment(Store()); true }
                R.id.cart -> { replaceFragment(Cart()); true }
                R.id.settings -> { replaceFragment(Settings()); true }
                else -> false

            }
        }

        /*val mainLayout = findViewById<LinearLayout>(R.id.main_layout)

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
        }*/
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}