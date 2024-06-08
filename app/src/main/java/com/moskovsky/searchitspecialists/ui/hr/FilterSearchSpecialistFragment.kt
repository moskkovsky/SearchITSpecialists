package com.moskovsky.searchitspecialists.ui.hr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentFilterSearchSpecialistBinding
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment


class FilterSearchSpecialistFragment : Fragment() {


    private var _binding: FragmentFilterSearchSpecialistBinding? = null
    private val binding: FragmentFilterSearchSpecialistBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterSearchSpecialistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDropDownMenus()
        showListITSpecialistsFragment()
    }

    private fun showListITSpecialistsFragment() {
        binding.btFilterSearchIT.setOnClickListener {
            val bundle = Bundle().apply {
                putString("cityUser", binding.etCityUser.text.toString())
                putString("specUser", binding.etSpecUser.text.toString())
                putString("ageUser", binding.etAgeUser.text.toString())
                putString("ageExperienceUser", binding.etAgeExperienceUser.text.toString())
                // график
                putString("chartExperienceUser", binding.etOfficeExpUser.text.toString())
                // стажировка
                putString("typeExperienceUser", binding.etFullExpUser.text.toString())
                putBoolean("hasHigherEducationUser", binding.sbUseListener.isChecked)
            }
            launchListITSpecialistsFragment(bundle)
        }
    }

    private fun setupDropDownMenus() {
        val officeExpAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.office_exp,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etOfficeExpUser.setAdapter(officeExpAdapter)

        val fullExpAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.full_exp,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etFullExpUser.setAdapter(fullExpAdapter)

        val experienceAgeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.experience_age,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etAgeExperienceUser.setAdapter(experienceAgeAdapter)

        val specAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spec,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.etSpecUser.setAdapter(specAdapter)
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
    companion object {
        private const val FRAGMENT_ERROR = "ProfileFragment is null"
        fun newInstance(): Fragment {
            return FilterSearchSpecialistFragment()
        }
    }
}