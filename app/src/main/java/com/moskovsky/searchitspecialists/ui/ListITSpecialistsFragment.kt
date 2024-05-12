package com.moskovsky.searchitspecialists.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentListITSpecialistsBinding


class ListITSpecialistsFragment : Fragment() {

    private var _binding: FragmentListITSpecialistsBinding? = null
    private val binding: FragmentListITSpecialistsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListITSpecialistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val FRAGMENT_ERROR = "ListITSpecialistsFragment is null"
        fun newInstance(): Fragment {
            return ListITSpecialistsFragment()
        }
    }
}