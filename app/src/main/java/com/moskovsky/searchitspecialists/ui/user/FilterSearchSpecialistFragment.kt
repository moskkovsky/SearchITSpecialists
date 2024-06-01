package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentFilterSearchSpecialistBinding
import com.moskovsky.searchitspecialists.databinding.FragmentProfileBinding
import com.moskovsky.searchitspecialists.ui.app.ProfileFragment
import com.moskovsky.searchitspecialists.ui.hr.AddVacancyFragment


class FilterSearchSpecialistFragment : Fragment() {


    private var _binding: FragmentFilterSearchSpecialistBinding? = null
    private val binding: FragmentFilterSearchSpecialistBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterSearchSpecialistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*//  Добавить вакансию
    private fun showListAddVacancyFragment() {
        binding..setOnClickListener {
            launchAddVacancyFragment()
        }
    }

    private fun launchAddVacancyFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, AddVacancyFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }*/

    companion object {
        private const val FRAGMENT_ERROR = "ProfileFragment is null"
        fun newInstance(): Fragment {
            return FilterSearchSpecialistFragment()
        }
    }
}