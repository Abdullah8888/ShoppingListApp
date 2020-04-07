package com.example.shoppinglistapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*
import kotlinx.android.synthetic.main.product_row.*
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val isInStock = intent.getStringExtra("is_in_stock")
        val image = intent.getStringExtra("image")


        val inputStream = ByteArrayInputStream(image.toByteArray(Charsets.UTF_8))
        val imageBitmap = BitmapFactory.decodeStream(inputStream)
        product_image.setImageBitmap(imageBitmap)

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