package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class Cart : Fragment() {
    private val json = JSONTools()

    @SuppressLint("DiscouragedApi", "SetTextI18n")
    private fun createProductCard(product: CartProduct) {
        val layout = view?.findViewById<LinearLayout>(R.id.cart_layout)
        Log.d("Cart", "1")
        val inflater = LayoutInflater.from(context)
        Log.d("Cart", "2")
        val view = inflater.inflate(R.layout.product_card_cart, layout, false)
        Log.d("Cart", "3")
        view.tag = product.product.name

        view.findViewById<TextView>(R.id.product_name).text = product.product.name
        view.findViewById<TextView>(R.id.product_price).text = "$${product.product.price}"
        view.findViewById<TextView>(R.id.product_nb).text = "x${product.quantity}"
        view.findViewById<TextView>(R.id.product_description).text = product.product.description
        val imageResId = resources.getIdentifier(product.product.image, "drawable", requireActivity().packageName)
        view.findViewById<ImageView>(R.id.product_image).setImageResource(imageResId)

        view.findViewById<TextView>(R.id.product_total).text = "$${product.getTotalPrice()}"

        view.findViewById<Button>(R.id.delete_button).setOnClickListener {
            json.removeProductFromCart(requireContext(), product.product)
            onResume()
        }

        layout?.addView(view)
    }

    private  fun totalCartPrice(): Int {
        val cart = json.readCartJson(requireContext())
        var total = 0
        cart.forEach { product ->
            total += product.getTotalPrice()
        }
        return total
    }

    override fun onResume() {
        super.onResume()
        val layout = view?.findViewById<LinearLayout>(R.id.cart_layout)
        val cart = json.readCartJson(requireContext())

        layout?.removeAllViews()

        cart.forEach { product ->
            Log.d("Cart", "Product: ${product.product.name}, Quantity: ${product.quantity}")
            createProductCard(product)
        }

        view?.findViewById<Button>(R.id.checkout_button)?.text = "\uD83D\uDED2 $${totalCartPrice()}"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
}