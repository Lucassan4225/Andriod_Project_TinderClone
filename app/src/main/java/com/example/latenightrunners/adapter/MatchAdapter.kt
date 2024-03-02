package com.example.latenightrunners.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.latenightrunners.R
import com.example.latenightrunners.data.User

class MatchAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            // Load user image using Glide library
            Glide.with(itemView.context)
                .load(user.imageUrl)
                .apply(
                    RequestOptions()
                        // Placeholder image while loading
                        // Error image if loading fails
                        .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache both original & resized image
                        .centerCrop()
                )
                .into(itemView.findViewById(R.id.userImage))
        }
    }
}
