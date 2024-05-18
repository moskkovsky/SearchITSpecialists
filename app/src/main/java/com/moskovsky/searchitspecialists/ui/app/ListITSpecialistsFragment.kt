package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentListITSpecialistsBinding
import com.moskovsky.searchitspecialists.ui.user.RegistrationPersonalInfoUserFragment


class ListITSpecialistsFragment : Fragment() {

    private var _binding: FragmentListITSpecialistsBinding? = null
    private val binding: FragmentListITSpecialistsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListITSpecialistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProfileFragment()
    }

    // Profile
    private fun showProfileFragment() {
        binding.iconProfile.setOnClickListener {
            launchProfileFragment()
        }
    }

    private fun launchProfileFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, ProfileFragment.newInstance())
            .commit()
    }

    companion object {
        private const val FRAGMENT_ERROR = "ListITSpecialistsFragment is null"
        fun newInstance(): Fragment {
            return ListITSpecialistsFragment()
        }
    }
}