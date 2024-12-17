package com.codegalaxy.pagingimpldec16.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codegalaxy.pagingandbottomnavigationimpl.databinding.ItemProductBinding
import com.codegalaxy.pagingimpldec16.model.dto.Product

class ProductAdapter : PagingDataAdapter<Product, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.PART_NUM == newItem.PART_NUM
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        product?.let { holder.bind(it) }
    }


    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {

            binding.partName.text = product.PART_NAME
            binding.partMrp.text = "₹${product.PART_MRP}"

            Glide.with(binding.thumbnail.context)
                .load(product.THUMBNAIL_IMAGE_URL)
                .into(binding.thumbnail)
        }
    }
}
