package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentRegistrationAccountUserBinding

class RegistrationAccountUserFragment : Fragment() {
    private var _binding: FragmentRegistrationAccountUserBinding? = null
    private val binding: FragmentRegistrationAccountUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationAccountUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRegistrationPersonalInfoUserFragment()
    }

    private fun showRegistrationPersonalInfoUserFragment() {
        binding.btNextInRegPersonalInfoUser.setOnClickListener {
            val email = binding.etEmailUser.text.toString()
            val password = binding.etUserPassword.text.toString()
            val passwordProof = binding.etPasswordProofUser.text.toString()

            if (validateInput(email, password, passwordProof)) {
                launchRegistrationSpecializationUserFragment(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String, passwordProof: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            binding.tilEmailUser.error = "Введите электронную почту"
            isValid = false
        } else {
            binding.tilEmailUser.error = null
        }

        if (password.isEmpty()) {
            binding.tilPasswordUser.error = "Введите пароль"
            isValid = false
        } else {
            binding.tilPasswordUser.error = null
        }

        if (passwordProof.isEmpty()) {
            binding.tilPasswordProofUser.error = "Подтвердите пароль"
            isValid = false
        } else {
            binding.tilPasswordProofUser.error = null
        }

        if (password != passwordProof) {
            binding.tilPasswordProofUser.error = "Пароли не совпадают"
            isValid = false
        } else {
            if (passwordProof.isNotEmpty()) {
                binding.tilPasswordProofUser.error = null
            }
        }

        return isValid
    }

    private fun launchRegistrationSpecializationUserFragment(email: String, password: String) {
        val fragment = RegistrationPersonalInfoUserFragment.newInstance(email, password)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "RegistrationAccountUserFragment is null"
        fun newInstance(): Fragment {
            return RegistrationAccountUserFragment()
        }
    }
}