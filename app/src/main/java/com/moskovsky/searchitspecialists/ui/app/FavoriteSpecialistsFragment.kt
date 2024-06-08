package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moskovsky.searchitspecialists.R
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

        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.selectedItemId = R.id.bottom_like


        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_list_spec -> {
                    launchListITSpecialistsFragment()
                    true
                }

                R.id.bottom_like -> {
                    launchFavoriteSpecialistsFragment()
                    true
                }

                R.id.bottom_notification -> {
                    launchFavoriteSpecialistsFragment()
                    true
                }

                R.id.bottom_categories -> {
                    launchCategoriesFragment()
                    true
                }

                else -> false
            }
        }
    }

    private fun launchListITSpecialistsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, ListITSpecialistsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
    private fun launchFavoriteSpecialistsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, FavoriteSpecialistsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    //Categories

    private fun launchCategoriesFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, CategoriesFragment.newInstance())
            .addToBackStack(null)
            .commit()
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