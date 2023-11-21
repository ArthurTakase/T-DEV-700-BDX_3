package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class Store : Fragment() {
    @SuppressLint("SetTextI18n", "DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)

        val mainLayout = view.findViewById<LinearLayout>(R.id.store_layout)
        val products = readProductsFromJson()

        // lie le fragment Cart.kt à l'activité principale
        val cartFragment = Cart()

        products.forEach { product ->
            inflater.inflate(R.layout.product_card, mainLayout, false).apply {
                findViewById<TextView>(R.id.product_name).text = product.name
                findViewById<TextView>(R.id.product_price).text = "$${product.price} - ADD TO CART"
                findViewById<TextView>(R.id.product_description).text = product.description
                val imageResId = resources.getIdentifier(product.image, "drawable", requireActivity().packageName)
                findViewById<ImageView>(R.id.product_image).setImageResource(imageResId)

                findViewById<Button>(R.id.product_price).setOnClickListener {
                    cartFragment.addToCart(product)
                    Log.d("Cart", "Added ${product.name} to cart")
                }

                mainLayout.addView(this)
            }
        }

        return view
    }

    private fun readProductsFromJson(): Array<Product> {
        val inputStream = requireContext().assets.open("products.json")
        val json = InputStreamReader(inputStream).readText()
        val gson = Gson()
        val productListType = object : TypeToken<Array<Product>>() {}.type
        inputStream.close()
        return gson.fromJson(json, productListType)
    }
}
