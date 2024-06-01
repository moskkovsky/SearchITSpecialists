package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentFilterSearchSpecialistBinding
import com.moskovsky.searchitspecialists.databinding.FragmentFilterSearchVacancyBinding
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment


class FilterSearchVacancyFragment : Fragment() {


    private var _binding: FragmentFilterSearchVacancyBinding? = null
    private val binding: FragmentFilterSearchVacancyBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterSearchVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListITSpecialistsFragment()
    }

    //  Главный экран
    private fun showListITSpecialistsFragment() {
        binding.btGoNextHomeScreen.setOnClickListener {
            launchListITSpecialistsFragment()
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

    companion object {
        private const val FRAGMENT_ERROR = "FilterSearchVacancyFragment is null"
        fun newInstance(): Fragment {
            return FilterSearchVacancyFragment()
        }
    }
}