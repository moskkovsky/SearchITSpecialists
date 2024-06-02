package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentCategoriesBinding
import com.moskovsky.searchitspecialists.domain.Categories


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding: FragmentCategoriesBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.selectedItemId = R.id.bottom_categories

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


    private fun setupRecyclerView() {
        val categoriesList = listOf(
            Categories("Backend"),
            Categories("Frontend"),
            Categories("DevOps"),
            Categories("UX/UI Design"),
            Categories("Android"),
            Categories("IOS"),
            Categories("Разработка игр"),
            Categories("QA"),
            Categories("Робототехника"),
            Categories("Безопасность"),
            Categories("Инженер"),
            Categories("Аналитик"),
            Categories("JavaScript"),
            Categories("Java"),
            Categories("Руководитель"),
            Categories("HR")
        )

        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategories.adapter = CategoriesAdapter(categoriesList) { category ->
            // Handle category click
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val FRAGMENT_ERROR = "FragmentCategoriesBinding is null"
        fun newInstance(): Fragment {
            return CategoriesFragment()
        }
    }

}