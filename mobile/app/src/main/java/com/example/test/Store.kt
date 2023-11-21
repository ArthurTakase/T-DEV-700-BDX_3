package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class Store : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)

        val mainLayout = view.findViewById<LinearLayout>(R.id.main_layout)

        val products = readProductsFromJson()

        for (product in products) {
            val productItem = inflater.inflate(R.layout.product_card, mainLayout, false)
            productItem.findViewById<TextView>(R.id.product_name).text = product.name
            productItem.findViewById<TextView>(R.id.product_price).text = "$${product.price} - ADD TO CART"
            productItem.findViewById<TextView>(R.id.product_description).text = product.description
            val imageResId = resources.getIdentifier(product.image, "drawable", requireActivity().packageName)
            productItem.findViewById<ImageView>(R.id.product_image).setImageResource(imageResId)

            mainLayout.addView(productItem)
        }

        return view
    }

    private fun readProductsFromJson(): Array<Product> {
        val inputStream = requireContext().assets.open("products.json")
        val json = InputStreamReader(inputStream).readText()
        val gson = Gson()
        val productListType = object : TypeToken<Array<Product>>() {}.type
        return gson.fromJson(json, productListType)
    }
}
