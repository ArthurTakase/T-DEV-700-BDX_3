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

    fun removeProductFromCart(context: Context, product: Product) {
        val cart = readCartJson(context)
        val existingProduct = cart.find { it.product.name == product.name }
        if (existingProduct != null) {
            cart.remove(existingProduct)
        }
        writeCartJson(context, cart)
    }

    data class User(val email: String, val server: String)

    public fun readUserJson(context: Context): User {
        val gson = Gson()
        val userFile = File(context.filesDir, "user.json")

        if (!userFile.exists()) {
            userFile.createNewFile()
            writeUserJson(context, User("", ""))
        }

        val json = userFile.bufferedReader().use { it.readText() }
        val userType = object : TypeToken<User>() {}.type
        return gson.fromJson(json, userType)
    }

    private fun writeUserJson(context: Context, user: User) {
        val gson = Gson()
        val userJson = gson.toJson(user)

        val userFile = File(context.filesDir, "user.json")
        if (!userFile.exists()) userFile.createNewFile()

        context.openFileOutput("user.json", Context.MODE_PRIVATE).use { it.write(userJson.toByteArray()) }
    }

    fun addUserEmail(context: Context, email: String) {
        val user = readUserJson(context)
        val updatedUser = user.copy(email = email)
        writeUserJson(context, updatedUser)
    }

    fun addUserServer(context: Context, server: String) {
        val user = readUserJson(context)
        val updatedUser = user.copy(server = server)
        writeUserJson(context, updatedUser)
    }
}
