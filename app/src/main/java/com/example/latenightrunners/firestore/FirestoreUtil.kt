package com.example.latenightrunners.firestore

import android.annotation.SuppressLint
import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

object FirestoreUtil {
    @SuppressLint("StaticFieldLeak")
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    fun saveData(collectionName: String, data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionName)
            .add(data)
            .addOnSuccessListener { documentReference ->
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun saveImage(collectionName: String, imageUri: Uri, imageName: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = storage.reference
        val imagesRef = storageRef.child("images/${UUID.randomUUID()}")

        val uploadTask = imagesRef.putFile(imageUri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl.addOnSuccessListener { uri ->
                val imageData = hashMapOf(
                    "image_name" to imageName,
                    "image_url" to uri.toString()
                )
                saveData(collectionName, imageData, onSuccess, onFailure)
            }.addOnFailureListener {
                onFailure(it)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }
}
