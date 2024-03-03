package com.example.latenightrunners.adapter//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.latenightrunners.databinding.ItemUserLayoutBinding
//import com.google.firebase.firestore.QueryDocumentSnapshot
//
//class com.example.latenightrunners.adapter.DatingAdapter(
//    val context: Context,
//    val list: ArrayList<QueryDocumentSnapshot>,
//    imageMap: HashMap<String, String>
//): RecyclerView.Adapter<com.example.latenightrunners.adapter.DatingAdapter.DatingViewHolder>() {
//
//    inner class DatingViewHolder(val binding: ItemUserLayoutBinding)
//        : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatingViewHolder {
//        return DatingViewHolder(ItemUserLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: DatingViewHolder, position: Int) {
//        val user = list[position].data // Assuming your document contains user data
//
//        // Set user name and age
//        holder.binding.swipeUserName.text = user?.get("name").toString()
//        holder.binding.swipeUserAge.text = user?.get("age").toString()
//
//        // Load user image using Glide
//        val userId = list[position].id
//        loadImageUrl(userId) { imageUrl ->
//            Glide.with(context).load(imageUrl).into(holder.binding.userImage)
//        }
//    }
//
//    private fun loadImageUrl(userId: String, onComplete: (String?) -> Unit) {
//        com.example.latenightrunners.firestore.FirestoreUtil.getProfileImageUri(userId,
//            onSuccess = { imageUrl ->
//                onComplete(imageUrl)
//            },
//            onFailure = { exception ->
//                Log.e("com.example.latenightrunners.adapter.DatingAdapter", "Error loading image URL: $exception")
//                onComplete(null)
//            }
//        )
//    }
//
//
//}
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latenightrunners.AddInfoActivity
import com.example.latenightrunners.databinding.ItemUserLayoutBinding
import com.example.latenightrunners.firestore.FirestoreUtil
import com.google.firebase.firestore.QueryDocumentSnapshot
class DatingAdapter(
    val context: Context,
    val list: ArrayList<QueryDocumentSnapshot>,
    imageMap: HashMap<String, String>
): RecyclerView.Adapter<DatingAdapter.DatingViewHolder>() {
    inner class DatingViewHolder(val binding: ItemUserLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatingViewHolder {
        return DatingViewHolder(ItemUserLayoutBinding.inflate(LayoutInflater.from(context),parent,false))
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: DatingViewHolder, position: Int) {
        val user = list[position].data

        holder.binding.swipeUserName.text = user?.get("name").toString()
        holder.binding.swipeUserAge.text = user?.get("age").toString()

        val userId = list[position].id
        loadImageUrl(userId) { imageUrl ->
            Glide.with(context).load(imageUrl).into(holder.binding.userImage)
        }
        holder.binding.UpButton.setOnClickListener {
            try {

                val intent = Intent(holder.itemView.context, AddInfoActivity::class.java)
                holder.itemView.context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("DatingAdapter", "Error starting AddInfoActivity: ${e.message}")
            }
        }
    }
    private fun loadImageUrl(userId: String, onComplete: (String?) -> Unit) {
        FirestoreUtil.getProfileImageUri(userId,
            onSuccess = { imageUrl ->
                onComplete(imageUrl)
            },
            onFailure = { exception ->
                Log.e("com.example.latenightrunners.adapter.DatingAdapter", "Error loading image URL: $exception")
                onComplete(null)
            }
        )
    }

}

