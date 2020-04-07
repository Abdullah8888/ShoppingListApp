package com.example.shoppinglistapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.model.Products
import java.io.ByteArrayOutputStream

class ProductAdapter(private val products: List<Products>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.photo)
        val price: TextView = itemView.findViewById(R.id.price)
        val isInStock: ImageView = itemView.findViewById(R.id.is_in_stock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder =  ProductViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetailsActivity::class.java)
            intent.putExtra("title", products[holder.adapterPosition].name)
            intent.putExtra("price", products[holder.adapterPosition].price)
            intent.putExtra("is_in_stock", products[holder.adapterPosition].isInStock)
            val bitmapImage = BitmapFactory.decodeStream(products[holder.adapterPosition].image)
//            val stream = ByteArrayOutputStream()
//            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, stream)
//            val byteArray = stream.toByteArray()
            intent.putExtra("image", bitmapImage)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {

        holder.name.text = products[position].name
        holder.price.text = products[position].price
        holder.image.setImageBitmap(BitmapFactory.decodeStream(products[position].image))
        if (products[position].isInStock == "no")  {
            holder.isInStock.visibility = View.GONE
        }

    }


}