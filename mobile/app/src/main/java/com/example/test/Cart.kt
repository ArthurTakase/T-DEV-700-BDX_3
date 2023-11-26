package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Cart : Fragment() {
    private val json = JSONTools()

    @SuppressLint("DiscouragedApi", "SetTextI18n")
    private fun createProductCard(product: CartProduct, layout: LinearLayout): View? {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.product_card_cart, layout, false)
        view.tag = product.product.name

        view.findViewById<TextView>(R.id.product_name).text = product.product.name
        view.findViewById<TextView>(R.id.product_price).text = "$${product.product.price}"
        view.findViewById<TextView>(R.id.product_nb).text = "x${product.quantity}"
        view.findViewById<TextView>(R.id.product_description).text = product.product.description
        val imageResId = resources.getIdentifier(product.product.image, "drawable", requireActivity().packageName)
        view.findViewById<ImageView>(R.id.product_image).setImageResource(imageResId)

        return view
    }

    override fun onResume() {
        super.onResume()
        val layout = view?.findViewById<LinearLayout>(R.id.cart_layout)
        val cart = json.readCartJson(requireContext())
        layout?.removeAllViews()

        cart.forEach { product -> createProductCard(product, layout!!) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
}