package com.example.shoppinglistapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*
import kotlinx.android.synthetic.main.product_row.*
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val isInStock = intent.getStringExtra("is_in_stock")

//        val byteArray = intent.getByteArrayExtra("image")
//        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
       val bitmapImage = intent.getParcelableExtra<Bitmap>("image")
        product_image.setImageBitmap(bitmapImage)

        product_price.text = price
        product_name.text = title

        if (isInStock == "no") {
            is_available.text = "This product is out of stock"
        }
        else {
            is_available.text = "Buy now"
        }

    }
}