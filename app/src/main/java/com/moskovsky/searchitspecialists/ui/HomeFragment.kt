package com.moskovsky.searchitspecialists.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentHomeBinding
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment
import com.moskovsky.searchitspecialists.ui.hr.RegistrationAccountHRFragment
import com.moskovsky.searchitspecialists.ui.user.RegistrationAccountUserFragment


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListITSpecialistsFragment()
        showRegisterUserFragment()
        showRegistrationAccountHRFragment()
    }

    // Главный экран
    private fun showListITSpecialistsFragment() {
        binding.btLoginInAccount.setOnClickListener {
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
            .commit()
    }

    //Экран регистрации пользователя
    private fun showRegisterUserFragment() {
        binding.btRegistrationAccountUser.setOnClickListener {
            launchRegisterUserFragment()
        }
    }

    private fun launchRegisterUserFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, RegistrationAccountUserFragment.newInstance())
            .commit()
    }


    // Экран регистрации HR
    private fun showRegistrationAccountHRFragment() {
        binding.btRegistrationAccountAsHR.setOnClickListener {
            launchRegistrationAccountHRFragment()
        }
    }

    private fun launchRegistrationAccountHRFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, RegistrationAccountHRFragment.newInstance())
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "Home is null"
        private const val REGISTRATION_USER_DATA_ERROR = "Change data"
        private const val REGISTRATION_EMPTY_DATA = "Not enough data"
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }

}