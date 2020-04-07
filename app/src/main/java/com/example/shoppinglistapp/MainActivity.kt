package com.example.shoppinglistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.shoppinglistapp.model.Products

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.InputStream
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.File
import android.os.Environment
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.graphics.drawable.Drawable
import android.media.ImageReader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager


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

        //Picasso.get().load("img/banner23.png").into(banner);

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
            Log.e("check1", "here i am")
            val imageInputStream = assets.open(imagePath)

            pro.add(Products(name, price, imageInputStream, isInStock))
        }

//        Picasso.get().load(R.drawable.banner222)
//                          .config(Bitmap.Config.RGB_565)
//                        .fit().centerCrop().into(banner);

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductAdapter(pro)
        }
        //banner.setImageResource(R.drawable.banner222)
    }
}

