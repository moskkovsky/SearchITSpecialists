package com.moskovsky.searchitspecialists.ui.hr

import android.content.Context
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
import com.moskovsky.searchitspecialists.databinding.FragmentRegistrationAccountHRBinding
import com.moskovsky.searchitspecialists.domain.User
import com.moskovsky.searchitspecialists.domain.UserSession
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment

class RegistrationAccountHRFragment : Fragment() {

    private var _binding: FragmentRegistrationAccountHRBinding? = null

    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    private val binding: FragmentRegistrationAccountHRBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private val viewModel: RegistrationHRViewModel by viewModels {
        RegistrationHRViewModelFactory(UserRepository(ApiFactory.apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationAccountHRBinding.inflate(inflater, container, false)
        sharedPreferencesManager = SharedPreferencesManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSaveButton()
    }

    private fun setupSaveButton() {
        binding.btNextInListItSpec.setOnClickListener {
            if (validateFields()) {
                saveUser()
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        val name = binding.etNameHr.text.toString().trim()
        val surname = binding.etSurnameHr.text.toString().trim()
        val telegram = binding.etTgHr.text.toString().trim()
        val email = binding.etEmailHr.text.toString().trim()
        val password = binding.etPasswordHr.text.toString().trim()
        val passwordProof = binding.etPasswordProofHr.text.toString().trim()

        if (name.isEmpty()) {
            binding.tilNameHr.error = "Необходимо заполнить"
            isValid = false
        } else {
            binding.tilNameHr.error = null
        }

        if (surname.isEmpty()) {
            binding.tilSurnameHr.error = "Необходимо заполнить"
            isValid = false
        } else {
            binding.tilSurnameHr.error = null
        }

        if (telegram.isEmpty()) {
            binding.tilTgHr.error = "Необходимо заполнить"
            isValid = false
        } else {
            binding.tilTgHr.error = null
        }

        if (email.isEmpty()) {
            binding.tilEmailHr.error = "Необходимо заполнить"
            isValid = false
        } else {
            binding.tilEmailHr.error = null
        }

        if (password.isEmpty()) {
            binding.tilPasswordHr.error = "Необходимо заполнить"
            isValid = false
        } else {
            binding.tilPasswordHr.error = null
        }

        if (passwordProof.isEmpty()) {
            binding.tilPasswordProofHr.error = "Необходимо заполнить"
            isValid = false
        } else {
            binding.tilPasswordProofHr.error = null
        }

        if (password != passwordProof) {
            binding.tilPasswordProofHr.error = "Пароли не совпадают"
            isValid = false
        } else {
            binding.tilPasswordProofHr.error = null
        }

        return isValid
    }

    private fun saveUser() {
        val user = User(
            name = binding.etNameHr.text.toString().trim(),
            surname = binding.etSurnameHr.text.toString().trim(),
            password = binding.etPasswordHr.text.toString().trim(),
            telegram = binding.etTgHr.text.toString().trim(),
            email = binding.etEmailHr.text.toString().trim()
        )

        viewModel.saveUser(user) { response ->
            if (response.isSuccessful) {
                sharedPreferencesManager.email = user.email
                sharedPreferencesManager.role = "ROLE_ADMIN"
                launchListITSpecialistsFragment()
            } else {
            }
        }
    }

    private fun saveToSharedPreferences(email: String, role: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("role", role)
        editor.apply()
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
        private const val FRAGMENT_ERROR = "RegistrationAccountHRFragment is null"
        fun newInstance(): Fragment {
            return RegistrationAccountHRFragment()
        }
    }
}