package com.example.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Cart : Fragment() {
    // create an array of CardProduct objects
    var cartProducts: MutableList<CardProduct> = mutableListOf()

    fun addToCart(product: Product) {
        val existingProduct = cartProducts.find { it.product.name == product.name }
        if (existingProduct != null) {
            existingProduct.quantity++
        } else {
            cartProducts.add(CardProduct(product, 1))
        }
    }

    fun getTotalPrice(): Int {
        return cartProducts.sumBy { it.getTotalPrice() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = Cart()
    }
}