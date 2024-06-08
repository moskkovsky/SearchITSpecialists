package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        setupDropDownMenus()
    }



    //  Главный экран
    private fun showListITSpecialistsFragment() {
        binding.btGoNextHomeScreen.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("title", binding.etTitleVacancy.text.toString())
                    putString("city", binding.etCityVacancy.text.toString())
                    putString("spec", binding.etSpecVacancy.text.toString())
                    putString("ageExperience", binding.etAgeExperienceVacancy.text.toString())
                    // график
                    putString("chartExperience", binding.etChartExperienceVacancy.text.toString())
                    // стажировка
                    putString("typeExperience", binding.etTypeExperienceVacancy.text.toString())
                    putBoolean("hasCompany", binding.sbUseListenerVacancy.isChecked)
                    putBoolean("hasHigherEducation", binding.sbUseListenerEducation.isChecked)
                }
                launchListITSpecialistsFragment(bundle)
        }
    }


    private fun launchListITSpecialistsFragment(bundle: Bundle) {
        val fragment = ListITSpecialistsFragment().apply {
            arguments = bundle
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupDropDownMenus() {
        val officeExpAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.office_exp,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etChartExperienceVacancy.setAdapter(officeExpAdapter)

        val fullExpAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.full_exp,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etTypeExperienceVacancy.setAdapter(fullExpAdapter)

        val experienceAgeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.experience_age,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etAgeExperienceVacancy.setAdapter(experienceAgeAdapter)

        val specAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spec,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etSpecVacancy.setAdapter(specAdapter)
    }





    companion object {
        private const val FRAGMENT_ERROR = "FilterSearchVacancyFragment is null"
        fun newInstance(): Fragment {
            return FilterSearchVacancyFragment()
        }
    }
}