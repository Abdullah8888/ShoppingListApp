package com.example.shoppinglistapp.model

import java.io.InputStream

data class Products (
    val price: String,
    val name: String,
    val image: InputStream,
    val isInStock: String
)