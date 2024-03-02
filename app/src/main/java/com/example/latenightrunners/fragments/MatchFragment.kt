import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.latenightrunners.adapter.MatchAdapter
import com.example.latenightrunners.data.User
import com.example.latenightrunners.databinding.MatchFragmentBinding
import com.example.latenightrunners.firestore.FirestoreUtil
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException

class MatchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MatchAdapter

    private var _binding: MatchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MatchFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        userArrayList = ArrayList()
        myAdapter = MatchAdapter(userArrayList)
        recyclerView.adapter = myAdapter

//        fetchMatchedUsersImages()

        return view
    }

//    private fun fetchMatchedUsersImages() {
//        FirestoreUtil.getMatchedUsers(
//            onSuccess = { matchedUsers ->
//                val matchedUserIds = matchedUsers.map { it.id }
//                FirestoreUtil.getImagesForUserIds(
//                    matchedUserIds,
//                    onSuccess = { imageUrls ->
//                        userArrayList.clear()
//                        for (imageUrl in imageUrls) {
//                            userArrayList.add(User(imageUrl))
//                        }
//                        myAdapter.notifyDataSetChanged()
//                    },
//                    onFailure = { exception ->
//                        Log.e("MatchFragment", "Error fetching matched users' images", exception)
//                    }
//                )
//            },
//            onFailure = { exception ->
//                Log.e("MatchFragment", "Error fetching matched users", exception)
//            }
//        )
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}
