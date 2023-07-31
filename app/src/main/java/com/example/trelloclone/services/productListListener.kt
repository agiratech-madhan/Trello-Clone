package com.example.trelloclone.services

import android.view.View
import com.example.trelloclone.models.ShoppingModels.Product

interface ProductListListener {
    fun onProductListItemClick(view: View, product: Product)

}