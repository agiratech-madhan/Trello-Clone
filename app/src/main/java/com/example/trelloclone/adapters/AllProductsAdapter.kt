package com.example.trelloclone.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trelloclone.databinding.ShoppingitemBinding
import com.example.trelloclone.models.ShoppingModels.AllProducts
import com.example.trelloclone.models.ShoppingModels.Product

class AllProductsAdapter(val allProducts: AllProducts) :
    RecyclerView.Adapter<AllProductsAdapter.AllProductsViewHoler>() {
    inner class AllProductsViewHoler(val itemBinding: ShoppingitemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(product: Product) {
            itemBinding.titleTextView.text = product.title
            itemBinding.imageView.setImageURI(Uri.parse(product.thumbnail))
            Glide.with(itemView).load(product.thumbnail).into(itemBinding.imageView)
            itemBinding.productCostTextView.text = "Rs. ${product.price.toString()}"
            itemBinding.ratingTextView.text = "${product.rating.toString()}/5"
            itemBinding.Discount.text = "Discount % : ${product.discountPercentage.toString()}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsViewHoler {


        return AllProductsViewHoler(
            ShoppingitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return allProducts.products.size
    }

    override fun onBindViewHolder(holder: AllProductsViewHoler, position: Int) {
        val productData = allProducts.products[position]
        holder.bindItem(productData)
    }
}