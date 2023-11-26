package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class Store : Fragment() {
    private val json = JSONTools()

    @SuppressLint("SetTextI18n", "DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)

        val fd = requireContext().assets.open("products.json")
        val products: Array<Product> = json.readProductsJson(fd)

        val mainLayout = view.findViewById<LinearLayout>(R.id.store_layout)

        products.forEach { product ->
            inflater.inflate(R.layout.product_card, mainLayout, false).apply {
                findViewById<TextView>(R.id.product_name).text = product.name
                findViewById<TextView>(R.id.product_price).text = "$${product.price} - ADD TO CART"
                findViewById<TextView>(R.id.product_description).text = product.description
                val imageResId = resources.getIdentifier(product.image, "drawable", requireActivity().packageName)
                findViewById<ImageView>(R.id.product_image).setImageResource(imageResId)

                findViewById<Button>(R.id.product_price).setOnClickListener {
                    json.addProductToCart(requireContext(), product)
                }

                mainLayout.addView(this)
            }
        }

        return view
    }
}
