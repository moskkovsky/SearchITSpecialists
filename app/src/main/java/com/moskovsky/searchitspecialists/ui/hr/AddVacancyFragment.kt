package com.moskovsky.searchitspecialists.ui.hr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentAddVacancyBinding
import com.moskovsky.searchitspecialists.domain.Vacancy
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment

class AddVacancyFragment : Fragment() {
    private var _binding: FragmentAddVacancyBinding? = null
    private val binding: FragmentAddVacancyBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)
    private val viewModel: AddVacancyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDropDownMenus()
        setupSaveButton()
        observeViewModel()
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

    private fun setupSaveButton() {
        binding.btGoNextHomeScreen.setOnClickListener {
            if (validateFields()) {
                val vacancy = Vacancy(
                    company = binding.sbUseListenerVacancy.isChecked,
                    title = binding.etTitleVacancy.text.toString().trim(),
                    description = binding.etDescriptionVacancy.text.toString().trim(),
                    city = binding.etCityVacancy.text.toString().trim(),
                    specialist = binding.etSpecVacancy.text.toString().trim(),
                    experience = binding.etAgeExperienceVacancy.text.toString().trim(),
                    employment_type = binding.etChartExperienceVacancy.text.toString().trim(),
                    hasHigherEducation = binding.sbUseListenerEducation.isChecked,
                    workSchedule = binding.etTypeExperienceVacancy.text.toString().trim()
                )
                viewModel.saveVacancy(vacancy)
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.etTitleVacancy.text.isNullOrEmpty()) {
            binding.tilTitleVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilTitleVacancy.error = null
        }

        if (binding.etDescriptionVacancy.text.isNullOrEmpty()) {
            binding.tilDescriptionVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilDescriptionVacancy.error = null
        }

        if (binding.etCityVacancy.text.isNullOrEmpty()) {
            binding.tilCityVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilCityVacancy.error = null
        }

        if (binding.etSpecVacancy.text.isNullOrEmpty()) {
            binding.tilSpecVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilSpecVacancy.error = null
        }

        if (binding.etAgeExperienceVacancy.text.isNullOrEmpty()) {
            binding.tilAgeExperienceVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilAgeExperienceVacancy.error = null
        }

        if (binding.etChartExperienceVacancy.text.isNullOrEmpty()) {
            binding.tilChartExperienceVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilChartExperienceVacancy.error = null
        }

        if (binding.etTypeExperienceVacancy.text.isNullOrEmpty()) {
            binding.tilTypeExperienceVacancy.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilTypeExperienceVacancy.error = null
        }

        return isValid
    }

    private fun observeViewModel() {
        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show()
            if (status == "Vacancy saved successfully") {
                launchListITSpecialistsFragment()
            }
        })
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
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "AddVacancyFragment is null"
        fun newInstance(): Fragment {
            return AddVacancyFragment()
        }
    }
}