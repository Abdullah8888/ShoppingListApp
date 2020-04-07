package com.example.shoppinglistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.shoppinglistapp.model.Products

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.InputStream
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.GridLayoutManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayBanner()

        displayHomeScreenProducts()

    }

    fun displayBanner() {
        val imagePath = "img/healthy_food.png"
        val imageBitmap = assets.open(imagePath)
        val imageDecodedStream = BitmapFactory.decodeStream(imageBitmap)
        banner.setImageBitmap(imageDecodedStream)

        banner2.bringToFront()

    }

    fun displayHomeScreenProducts() {

        val pro = arrayListOf<Products>()

        val inputStream: InputStream = assets.open("ProductData.json")
        val productJson = inputStream.bufferedReader().use{it.readText()}
        val productArray = JSONArray(productJson)

        for (i in 0..productArray.length()-1) {
            val name = productArray.getJSONObject(i).getString("name")
            val price  = productArray.getJSONObject(i).getString("price")
            val imagePath = productArray.getJSONObject(i).getString("image")
            val isInStock = productArray.getJSONObject(i).getString("is_in_stock")
            val imageInputStream = assets.open(imagePath)

            pro.add(Products(name, price, imageInputStream, isInStock))
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductAdapter(pro)
        }

    }
}

