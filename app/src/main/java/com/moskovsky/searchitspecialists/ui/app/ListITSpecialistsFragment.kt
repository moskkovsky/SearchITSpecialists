package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.data.ApiFactory
import com.moskovsky.searchitspecialists.databinding.FragmentListITSpecialistsBinding
import com.moskovsky.searchitspecialists.ui.SpecialistsAdapter
import com.moskovsky.searchitspecialists.ui.VacancyAdapter
import com.moskovsky.searchitspecialists.ui.hr.AddVacancyFragment
import com.moskovsky.searchitspecialists.ui.user.FilterSearchSpecialistFragment
import com.moskovsky.searchitspecialists.ui.user.FilterSearchVacancyFragment
import com.moskovsky.searchitspecialists.ui.user.RegistrationPersonalInfoUserFragment
import kotlinx.coroutines.launch


class ListITSpecialistsFragment : Fragment() {

    private var _binding: FragmentListITSpecialistsBinding? = null
    private val binding: FragmentListITSpecialistsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListITSpecialistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fetchVacancies()
        fetchSpecialists()
        showListAddVacancyFragment()
        showProfileFragment()
        showFavoriteSpecialistsFragment()
        showCategoriesFragment()
        showFilterSearchVacancyFragment()
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

    //  Добавить вакансию
    private fun showListAddVacancyFragment() {
        binding.addVacancy.setOnClickListener {
            launchAddVacancyFragment()
        }
    }

    private fun launchAddVacancyFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, AddVacancyFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    //  Favorite
    private fun showFavoriteSpecialistsFragment() {
        binding.favorite.setOnClickListener {
            launchFavoriteSpecialistsFragment()
        }
    }

    private fun launchFavoriteSpecialistsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, FavoriteSpecialistsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    //Categories
    private fun showCategoriesFragment() {
        binding.categories.setOnClickListener {
            launchCategoriesFragment()
        }
    }

    private fun launchCategoriesFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, CategoriesFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    // Фильтры
    private fun showFilterSearchVacancyFragment() {
        binding.iconFilter.setOnClickListener {
            launchFilterSearchVacancyFragment()
        }
    }

    private fun launchFilterSearchVacancyFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, FilterSearchVacancyFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun fetchSpecialists() {
        lifecycleScope.launch {
            val response = ApiFactory.apiService.getAllUsers()
            if (response.isSuccessful) {
                val specialists = response.body() ?: emptyList()
                binding.rvSpecList.layoutManager = LinearLayoutManager(context)
                binding.rvSpecList.adapter = SpecialistsAdapter(specialists)
            }
        }
    }


    /*private fun fetchVacancies() {
        lifecycleScope.launch {
            val response = ApiFactory.apiService.getAllVacancies()
            if (response.isSuccessful) {
                val vacancies = response.body() ?: emptyList()
                binding.rvSpecList.layoutManager = LinearLayoutManager(context)
                binding.rvSpecList.adapter = VacancyAdapter(vacancies)
            }
        }
    }*/

    companion object {
        private const val FRAGMENT_ERROR = "ListITSpecialistsFragment is null"
        fun newInstance(): Fragment {
            return ListITSpecialistsFragment()
        }
    }
}