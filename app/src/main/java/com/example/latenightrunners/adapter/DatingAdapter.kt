package com.example.latenightrunners.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latenightrunners.databinding.ItemUserLayoutBinding
import com.example.latenightrunners.firestore.UserModel

class DatingAdapter(val context : Context, val list : ArrayList<UserModel>): RecyclerView.Adapter<DatingAdapter.DatingViewHolder>() {
    inner class DatingViewHolder(val binding: ItemUserLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatingViewHolder {

        return DatingViewHolder(ItemUserLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DatingViewHolder, position: Int) {

        holder.binding.swipeUserName.text = list[position].name
        holder.binding.swipeUserAge.text = list[position].age

        Glide.with(context).load(list[position].image).into(holder.binding.userImage)
    }
}