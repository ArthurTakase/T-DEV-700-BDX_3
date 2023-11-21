package com.example.test

class CardProduct(var product: Product, var quantity: Int) {
    fun getTotalPrice(): Int {
        return product.price * quantity
    }
}

