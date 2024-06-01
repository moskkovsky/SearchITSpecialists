package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentRegistrationPersonalInfoUserBinding


class RegistrationPersonalInfoUserFragment : Fragment() {
    private var _binding: FragmentRegistrationPersonalInfoUserBinding? = null
    private val binding: FragmentRegistrationPersonalInfoUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationPersonalInfoUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString(ARG_EMAIL)
        val password = arguments?.getString(ARG_PASSWORD)

        // Используйте email и password по необходимости

        setupDropdownMenus()
        showRegistrationSpecializationUserFragment()
        setupTextChangeListeners()
    }

    private fun setupDropdownMenus() {
        // Настройка выпадающего меню для "Тип занятости"
        val fullExpAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.full_exp,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etFullExpUser.setAdapter(fullExpAdapter)

        // Настройка выпадающего меню для "График работы"
        val officeExpAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.office_exp,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etOfficeExpUser.setAdapter(officeExpAdapter)

        // Настройка выпадающего меню для "Опыт работы"
        val experienceAgeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.experience_age,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etAgeExperienceUser.setAdapter(experienceAgeAdapter)
    }

    private fun showRegistrationSpecializationUserFragment() {
        binding.btNextInRegPersonalInfoUser.setOnClickListener {
            if (validateFields()) {
                val name = binding.etNameUser.text.toString()
                val surname = binding.etUserSurname.text.toString()
                val telegram = binding.etTgSurname.text.toString()
                val age = binding.etAgeUser.text.toString()
                val experience = binding.etAgeExperienceUser.text.toString()
                val city = binding.etCityUser.text.toString()
                val employmentType = binding.etOfficeExpUser.text.toString()
                val workSchedule = binding.etFullExpUser.text.toString()
                val educationInstitution = binding.etEducUser.text.toString()
                val experienceDescription = binding.etDescriptionUser.text.toString()
                val hasHigherEducation = binding.sbUseListener.isChecked


                // Передайте данные в следующий фрагмент или обработайте по необходимости
                launchRegistrationSpecializationUserFragment()
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.etNameUser.text.isNullOrEmpty()) {
            binding.tilNameUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilNameUser.error = null
        }

        if (binding.etUserSurname.text.isNullOrEmpty()) {
            binding.tilSurnameUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilSurnameUser.error = null
        }

        if (binding.etTgSurname.text.isNullOrEmpty()) {
            binding.tilTgUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilTgUser.error = null
        }

        if (binding.etAgeUser.text.isNullOrEmpty()) {
            binding.tilAgeUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilAgeUser.error = null
        }

        if (binding.etAgeExperienceUser.text.isNullOrEmpty()) {
            binding.tilAgeExperienceUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilAgeExperienceUser.error = null
        }

        if (binding.etCityUser.text.isNullOrEmpty()) {
            binding.tilCityUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilCityUser.error = null
        }

        if (binding.etFullExpUser.text.isNullOrEmpty()) {
            binding.tilFullExpUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilFullExpUser.error = null
        }

        if (binding.etOfficeExpUser.text.isNullOrEmpty()) {
            binding.tilOfficeExpUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilOfficeExpUser.error = null
        }

        if (binding.etEducUser.text.isNullOrEmpty()) {
            binding.tilEducUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilEducUser.error = null
        }

        if (binding.etDescriptionUser.text.isNullOrEmpty()) {
            binding.tilDescriptionUser.error = "Поле обязательно для заполнения"
            isValid = false
        } else {
            binding.tilDescriptionUser.error = null
        }

        return isValid
    }

    private fun setupTextChangeListeners() {
        setupTextChangedListener(binding.tilNameUser)
        setupTextChangedListener(binding.tilSurnameUser)
        setupTextChangedListener(binding.tilTgUser)
        setupTextChangedListener(binding.tilAgeUser)
        setupTextChangedListener(binding.tilAgeExperienceUser)
        setupTextChangedListener(binding.tilCityUser)
        setupTextChangedListener(binding.tilFullExpUser)
        setupTextChangedListener(binding.tilOfficeExpUser)
        setupTextChangedListener(binding.tilEducUser)
        setupTextChangedListener(binding.tilDescriptionUser)
    }

    private fun setupTextChangedListener(inputLayout: TextInputLayout) {
        inputLayout.editText?.doAfterTextChanged {
            inputLayout.error = null
        }
    }

    private fun launchRegistrationSpecializationUserFragment() {
        if (validateFields()) {
            val name = binding.etNameUser.text.toString()
            val surname = binding.etUserSurname.text.toString()
            val telegram = binding.etTgSurname.text.toString()
            val age = binding.etAgeUser.text.toString()
            val experience = binding.etAgeExperienceUser.text.toString()
            val city = binding.etCityUser.text.toString()
            val employmentType = binding.etOfficeExpUser.text.toString()
            val workSchedule = binding.etFullExpUser.text.toString()
            val educationInstitution = binding.etEducUser.text.toString()
            val experienceDescription = binding.etDescriptionUser.text.toString()
            val hasHigherEducation = binding.sbUseListener.isChecked
            val email = arguments?.getString(ARG_EMAIL)
            val password = arguments?.getString(ARG_PASSWORD)

            val bundle = Bundle().apply {
                putString("name", name)
                putString("surname", surname)
                putString("telegram", telegram)
                putString("age", age)
                putString("experience", experience)
                putString("city", city)
                putString("employmentType", employmentType)
                putString("workSchedule", workSchedule)
                putString("educationInstitution", educationInstitution)
                putString("experienceDescription", experienceDescription)
                putBoolean("hasHigherEducation", hasHigherEducation)
                putString(ARG_EMAIL, email)
                putString(ARG_PASSWORD, password)
            }

            val fragment = RegistrationSpecializationUserFragment().apply {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_EMAIL = "email"
        private const val ARG_PASSWORD = "password"
        private const val FRAGMENT_ERROR = "RegistrationPersonalInfoUserFragment is null"

        fun newInstance(email: String, password: String): RegistrationPersonalInfoUserFragment {
            val fragment = RegistrationPersonalInfoUserFragment()
            val args = Bundle().apply {
                putString(ARG_EMAIL, email)
                putString(ARG_PASSWORD, password)
            }
            fragment.arguments = args
            return fragment
        }
    }
}