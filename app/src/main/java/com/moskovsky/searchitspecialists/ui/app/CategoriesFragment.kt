package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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