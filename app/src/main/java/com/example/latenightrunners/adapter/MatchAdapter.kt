package com.example.latenightrunners.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latenightrunners.databinding.ItemUserLayoutBinding
import com.example.latenightrunners.databinding.MatchedItemBinding

class MatchAdapter(
    private val context: Context,
    private var imageList: MutableList<String>
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: MatchedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val imageUrl = imageList[position]

        // Load image using Glide
        Glide.with(context)
            .load(imageUrl)
            .into(holder.binding.ivUserImage)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun setImageList(images: List<String>) {
        this.imageList.clear()
        this.imageList.addAll(images)
        notifyDataSetChanged()
    }
}

