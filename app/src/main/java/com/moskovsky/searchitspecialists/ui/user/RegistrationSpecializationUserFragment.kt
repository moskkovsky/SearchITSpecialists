package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentRegistrationSpecializationUserBinding

class RegistrationSpecializationUserFragment : Fragment() {
    private var _binding: FragmentRegistrationSpecializationUserBinding? = null
    private val binding get() = _binding!!

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
    private var hasHigherEducation: Boolean = true
    private var email: String? = null
    private var password: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationSpecializationUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this. name = arguments?.getString("name")
        this. surname = arguments?.getString("surname")
        this. telegram = arguments?.getString("telegram")
        this. age = arguments?.getString("age")
        this. experience = arguments?.getString("experience")
        this. city = arguments?.getString("city")
        this. employmentType = arguments?.getString("employmentType")
        this. workSchedule = arguments?.getString("workSchedule")
        this. educationInstitution = arguments?.getString("educationInstitution")
        this. experienceDescription = arguments?.getString("experienceDescription")
        this. hasHigherEducation = arguments?.getBoolean("hasHigherEducation") ?: true
        this. email = arguments?.getString(ARG_EMAIL)
        this. password = arguments?.getString(ARG_PASSWORD)

        // Используйте полученные данные по необходимости

        setupRadioButtons()
        setupNextButton()
    }

    private fun setupRadioButtons() {
        binding.rbBack.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbFront.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbDevops.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbDesign.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbAndroid.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbIos.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbGame.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbQA.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbRobot.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbSecurity.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbEngineer.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbAnal.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbJavaScript.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbJava.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbPython.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbML.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbOrder.setOnClickListener { clearOtherRadioButtons(it) }
        binding.rbHR.setOnClickListener { clearOtherRadioButtons(it) }
    }

    private fun setupNextButton() {
        binding.btNextInRegSpecializationUser.setOnClickListener {
            val selectedText = "Backend"
            val bundle = Bundle().apply {
                putString("selectedText", selectedText)
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
                putString("email", email)
                putString("password", password)
                putString("specialist", selectedText)
            }

            val fragment = RegistrationStackUserFragment.newInstance().apply {
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
                .commit()
        }
    }

    private fun getSelectedRadioButtonText(): String {
        val selectedRadioButton = binding.root.findViewById<RadioButton>(
            binding.radioGroup.checkedRadioButtonId
        )
        return selectedRadioButton.text.toString()
    }

    private fun clearOtherRadioButtons(clickedButton: View) {
        val radioButtons = listOf(
            binding.rbBack,
            binding.rbFront,
            binding.rbJava,
            binding.rbPython,
            binding.rbML,
            binding.rbOrder,
            binding.rbHR,
            binding.rbDevops,
            binding.rbDesign,
            binding.rbAndroid,
            binding.rbIos,
            binding.rbGame,
            binding.rbQA,
            binding.rbRobot,
            binding.rbSecurity,
            binding.rbEngineer,
            binding.rbAnal,
            binding.rbJavaScript
        )
        radioButtons.filter { it != clickedButton }.forEach {
            it.isChecked = false
        }
        val selectedRadioButton = binding.root.findViewById<RadioButton>(clickedButton.id)
        val selectedText = selectedRadioButton.text
        Log.d("RadioButtonSelection", "Selected Text: $selectedText")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_EMAIL = "email"
        private const val ARG_PASSWORD = "password"

        fun newInstance(): RegistrationSpecializationUserFragment {
            return RegistrationSpecializationUserFragment()
        }
    }
}