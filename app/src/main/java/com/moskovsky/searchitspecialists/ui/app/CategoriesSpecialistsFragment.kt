package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.moskovsky.searchitspecialists.databinding.FragmentCategoriesSpecialistsBinding
import com.moskovsky.searchitspecialists.domain.User

class CategoriesSpecialistsFragment : Fragment() {

    private var _binding: FragmentCategoriesSpecialistsBinding? = null
    private val binding: FragmentCategoriesSpecialistsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesSpecialistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val booksSerializable = arguments?.getSerializable("books")
        val books: List<User> = if (booksSerializable is List<*>) {
            booksSerializable.filterIsInstance<User>()
        } else {
            emptyList()
        }
        Log.d("ListGenreOrBookInfoFragment", "Books received: $books") // Добавляем лог
        val adapter = CategoriesSpecialistsAdapter(books)
        binding.rvSpecList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpecList.adapter = adapter

    }

    companion object {
        private const val FRAGMENT_ERROR = "CategoriesSpecialistsFragment is null"
        fun newInstance(): Fragment {
            return CategoriesSpecialistsFragment()
        }
    }
}