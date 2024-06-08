package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.data.ApiFactory
import com.moskovsky.searchitspecialists.data.SharedPreferencesManager
import com.moskovsky.searchitspecialists.data.UserRepository
import com.moskovsky.searchitspecialists.databinding.FragmentRegistrationStackUserBinding
import com.moskovsky.searchitspecialists.domain.User
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment


class RegistrationStackUserFragment : Fragment() {

    private var _binding: FragmentRegistrationStackUserBinding? = null
    private val binding: FragmentRegistrationStackUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private val viewModel: RegistrationStackViewModel by viewModels {
        RegistrationStackViewModelFactory(UserRepository(ApiFactory.apiService))
    }

    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    private var selectedTechnologies: List<String>? = null
    private var name: String? = null
    private var surname: String? = null
    private var telegram: String? = null
    private var age: String? = null
    private var experience: String? = null
    private var city: String? = null
    private var employmentType: String? = null
    private var workSchedule: String? = null
    private var educationInstitution: String? = null
    private var experienceDescription: String? = null
    private var hasHigherEducation: Boolean = false
    private var email: String? = null
    private var password: String? = null
    private var specialist: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationStackUserBinding.inflate(inflater, container, false)
        sharedPreferencesManager = SharedPreferencesManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args ->
            selectedTechnologies = args.getStringArrayList("selectedTechnologies")
            name = args.getString("name")
            surname = args.getString("surname")
            telegram = args.getString("telegram")
            age = args.getString("age")
            experience = args.getString("experience")
            city = args.getString("city")
            employmentType = args.getString("employmentType")
            workSchedule = args.getString("workSchedule")
            educationInstitution = args.getString("educationInstitution")
            experienceDescription = args.getString("experienceDescription")
            hasHigherEducation = args.getBoolean("hasHigherEducation")
            email = args.getString("email")
            password = args.getString("password")
            specialist = args.getString("specialist")

        }
        setupSaveButton()
    }

    private fun setupSaveButton() {
        binding.btNextInRegSpecializationUser.setOnClickListener {
            val selectedTechnologies = getSelectedTechnologies()
            val user = User(
                name = name ?: "",
                surname = surname ?: "",
                password = password ?: "",
                telegram = telegram,
                age = age,
                experience = experience,
                city = city,
                education = educationInstitution,
                email = email ?: "",
                hasHigherEducation = hasHigherEducation,
                workSchedule = workSchedule,
                experienceDescription = experienceDescription,
                employmentType = employmentType,
                specialist = specialist,
                selectedTechnologies = selectedTechnologies
            )
            saveUser(user)
        }
    }

    private fun getSelectedTechnologies(): List<String> {
        val selectedTechnologies = mutableListOf<String>()
        val checkBoxes = listOf(
            binding.checkbox1,
            binding.checkbox2,
            binding.checkbox3,
            binding.checkbox4,
            binding.checkbox5,
            binding.checkbox6,
            binding.checkbox7,
            binding.checkbox8,
            binding.checkbox9,
            binding.checkbox10,
            binding.checkbox11,
            binding.checkbox12,
            binding.checkbox13,
            binding.checkbox14,
            binding.checkbox15,
            binding.checkbox16,
            binding.checkbox17,
            binding.checkbox18,
            binding.checkbox19,
            binding.checkbox20,
            binding.checkbox21,
            binding.checkbox22,
            binding.checkbox23,
            binding.checkbox24
        )

        for (checkBox in checkBoxes) {
            if (checkBox.isChecked) {
                selectedTechnologies.add(checkBox.text.toString())
            }
        }

        return selectedTechnologies
    }

    private fun saveUser(user: User) {
        viewModel.saveUser(user) { response ->
            if (response.isSuccessful) {
                sharedPreferencesManager.email = user.email
                sharedPreferencesManager.role = "ROLE_USER"
                launchRegistrationCompetenciesUserFragment()
            } else {
            }
        }
    }

    private fun launchRegistrationCompetenciesUserFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, RegistrationCompetenciesUserFragment.newInstance())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "RegistrationStackUserFragment is null"
        fun newInstance(): Fragment {
            return RegistrationStackUserFragment()
        }
    }
}
