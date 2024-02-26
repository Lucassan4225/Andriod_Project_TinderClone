//package com.example.latenightrunners.firestore
//
//import android.annotation.SuppressLint
//import android.net.Uri
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.FirebaseStorage
//import java.util.*
//
//object FirestoreUtil {
//    @SuppressLint("StaticFieldLeak")
//    private val db = FirebaseFirestore.getInstance()
//    private val storage = FirebaseStorage.getInstance()
//
//    fun saveData(collectionName: String, documentId: String, data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
//        db.collection(collectionName)
//            .document(documentId)
//            .set(data)
//            .addOnSuccessListener {
//                onSuccess()
//            }
//            .addOnFailureListener { e ->
//                onFailure(e)
//            }
//    }
//
//    fun saveImage(collectionName: String, imageUri: Uri, imageName: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
//        val storageRef = storage.reference
//        val imagesRef = storageRef.child("images/${UUID.randomUUID()}")
//
//        val uploadTask = imagesRef.putFile(imageUri)
//        uploadTask.addOnSuccessListener { taskSnapshot ->
//            imagesRef.downloadUrl.addOnSuccessListener { uri ->
//                val imageData = hashMapOf(
//                    "image_name" to imageName,
//                    "image_url" to uri.toString()
//                )
//                // For saving image data, you can implement as needed
//                // This function specifically deals with saving data to Firestore
//                // You can save the image URL or any other related data in Firestore
//                // If needed, you can call saveData() here passing the appropriate collectionName, documentId, and imageData
//                // For simplicity, I'm not including this call here as it may vary based on your application structure
//                onSuccess()
//            }.addOnFailureListener {
//                onFailure(it)
//            }
//        }.addOnFailureListener { exception ->
//            onFailure(exception)
//        }
//    }
//    fun getUserId(): String {
//        // Get the current user from Firebase Authentication
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        // Return the user ID if the user is authenticated, or return "unknownuser" if no user is found
//        return currentUser?.uid ?: "unknownuser"
//    }
//
//}
package com.example.latenightrunners.firestore

import android.annotation.SuppressLint
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

object FirestoreUtil {
    @SuppressLint("StaticFieldLeak")
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    fun saveData(collectionName: String, documentId: String, data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionName)
            .document(documentId)
            .set(data)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun saveImage(collectionName: String, documentId: String, imageUri: Uri, imageName: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = storage.reference
        val imagesRef = storageRef.child("images/${UUID.randomUUID()}")

        val uploadTask = imagesRef.putFile(imageUri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl.addOnSuccessListener { uri ->
                val imageData = hashMapOf(
                    "image_name" to imageName,
                    "image_url" to uri.toString()
                )
                db.collection(collectionName)
                    .document(documentId)
                    .set(imageData)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        onFailure(e)
                    }
            }.addOnFailureListener {
                onFailure(it)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }
    fun getProfileImageUri(userId: String, onSuccess: (String?) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                val imageUrl = document.getString("profile_image_url")
                onSuccess(imageUrl)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun getUserId(): String {
        // Get the current user from Firebase Authentication
        val currentUser = FirebaseAuth.getInstance().currentUser
        // Return the user ID if the user is authenticated, or return "unknownuser" if no user is found
        return currentUser?.uid ?: "unknownuser"
    }
}
