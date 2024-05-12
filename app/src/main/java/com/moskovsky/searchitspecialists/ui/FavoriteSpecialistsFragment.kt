package com.moskovsky.searchitspecialists.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentFavoriteSpecialistsBinding


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