package com.example.test

class CartProduct(var product: Product, var quantity: Int) {
    fun getTotalPrice(): Float {
        return product.price * quantity
    }
}

