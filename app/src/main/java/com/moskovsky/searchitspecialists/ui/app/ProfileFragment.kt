package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.data.ApiFactory.apiService
import com.moskovsky.searchitspecialists.data.SharedPreferencesManager
import com.moskovsky.searchitspecialists.databinding.FragmentProfileBinding
import com.moskovsky.searchitspecialists.ui.HomeFragment
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private lateinit var sharedPreferencesManager: SharedPreferencesManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferencesManager = SharedPreferencesManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListAddVacancyFragment()
    }

    private fun showListAddVacancyFragment() {
        binding.btLogOut.setOnClickListener {
            launchHomeFragment()
        }
    }
    private fun launchHomeFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun fetchUserData() {
        val email = sharedPreferencesManager.email
        if (email != null) {
            lifecycleScope.launch {
                val response = apiService.getUserByEmail(email)
                if (response.isSuccessful) {
                    val user = response.body()
                    // Update UI with user data
                    if (user != null) {
                        binding.tvProfileName.text = user.name
                        binding.tvProfileSurname.text = user.surname
                        binding.tvProfileEmail.text = user.email
                        binding.tvProfileTg.text = user.telegram
                    }
                } else {
                    // Handle error
                }
            }
        }
    }

    companion object {
        private const val FRAGMENT_ERROR = "ProfileFragment is null"
        fun newInstance(): Fragment {
            return ProfileFragment()
        }
    }
}