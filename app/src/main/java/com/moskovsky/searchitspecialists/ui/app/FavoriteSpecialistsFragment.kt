package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moskovsky.searchitspecialists.data.ApiFactory
import com.moskovsky.searchitspecialists.databinding.FragmentFavoriteSpecialistsBinding
import com.moskovsky.searchitspecialists.domain.FavoriteHR
import com.moskovsky.searchitspecialists.ui.SpecialistsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FavoriteSpecialistsFragment : Fragment() {
    private var _binding: FragmentFavoriteSpecialistsBinding? = null
    private val binding: FragmentFavoriteSpecialistsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteSpecialistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchFavorites()
    }

    private fun fetchFavorites() {
        val apiService = ApiFactory.apiService
        apiService.getAllFavorites().enqueue(object : Callback<List<FavoriteHR>> {
            override fun onResponse(call: Call<List<FavoriteHR>>, response: Response<List<FavoriteHR>>) {
                if (response.isSuccessful) {
                    val favorites = response.body() ?: emptyList()
                    binding.rvLike.adapter = FavoriteAdapter(favorites)
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<List<FavoriteHR>>, t: Throwable) {
                // Handle the failure
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val FRAGMENT_ERROR = "FavoriteNewsFragment is null"
        fun newInstance(): Fragment {
            return FavoriteSpecialistsFragment()
        }
    }
}