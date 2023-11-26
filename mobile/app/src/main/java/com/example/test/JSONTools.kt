package com.example.test

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream

class JSONTools() {
    fun readProductsJson(inputStream: InputStream): Array<Product> {
        val json = InputStreamReader(inputStream).readText()
        val gson = Gson()
        val productListType = object : TypeToken<Array<Product>>() {}.type
        inputStream.close()
        return gson.fromJson(json, productListType)
    }

    fun readCartJson(context: Context): MutableList<CartProduct> {
        val gson = Gson()

        return try {
            val cartJson = context.openFileInput("cart.json").bufferedReader().use { it.readText() }
            val cartProductListType = object : TypeToken<MutableList<CartProduct>>() {}.type
            gson.fromJson(cartJson, cartProductListType)
        } catch (e: FileNotFoundException) {
            mutableListOf()
        }
    }

    private fun writeCartJson(context: Context, cart: MutableList<CartProduct>) {
        val gson = Gson()
        val cartJson = gson.toJson(cart)

        val cartFile = File(context.filesDir, "cart.json")
        if (!cartFile.exists()) cartFile.createNewFile()

        val outputStream: OutputStream = context.openFileOutput("cart.json", Context.MODE_PRIVATE)
        outputStream.write(cartJson.toByteArray())
        outputStream.close()
    }

    fun addProductToCart(context: Context, product: Product) {
        val cart = readCartJson(context)
        val existingProduct = cart.find { it.product.name == product.name }
        if (existingProduct != null) {
            existingProduct.quantity++
        } else {
            cart.add(CartProduct(product, 1))
        }
        writeCartJson(context, cart)
    }
}
