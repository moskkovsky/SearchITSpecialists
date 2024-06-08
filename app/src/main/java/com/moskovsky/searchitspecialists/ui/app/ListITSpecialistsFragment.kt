package com.moskovsky.searchitspecialists.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.data.ApiFactory
import com.moskovsky.searchitspecialists.data.SharedPreferencesManager
import com.moskovsky.searchitspecialists.databinding.FragmentListITSpecialistsBinding
import com.moskovsky.searchitspecialists.ui.SpecialistsAdapter
import com.moskovsky.searchitspecialists.ui.VacancyAdapter
import com.moskovsky.searchitspecialists.ui.hr.AddVacancyFragment
import com.moskovsky.searchitspecialists.ui.hr.FilterSearchSpecialistFragment
import com.moskovsky.searchitspecialists.ui.user.FilterSearchVacancyFragment
import kotlinx.coroutines.launch


class ListITSpecialistsFragment : Fragment() {

    private var _binding: FragmentListITSpecialistsBinding? = null
    private val binding: FragmentListITSpecialistsBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private var filterData: Bundle? = null

    private lateinit var sharedPreferencesManager: SharedPreferencesManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filterData = arguments
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListITSpecialistsBinding.inflate(inflater, container, false)
        sharedPreferencesManager = SharedPreferencesManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (sharedPreferencesManager.isAdmin) {
            fetchSpecialists()
            showListAddVacancyFragment()
            showFilterSearchSpecialistFragment()
            showProfileFragment()
        } else {
            fetchVacancies()
            showFilterSearchVacancyFragment()
            binding.addVacancy.visibility = View.GONE
        }

        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.bottom_list_spec

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_list_spec -> {
                    true
                }
                R.id.bottom_like -> {
                    launchFavoriteSpecialistsFragment()
                    // Здесь можно открыть фрагменты или выполнять другие действия
                    true
                }
                R.id.bottom_notification -> {
                    launchFavoriteSpecialistsFragment()
                    // Здесь можно открыть фрагменты или выполнять другие действия
                    true
                }
                R.id.bottom_categories -> {
                    launchCategoriesFragment()
                    // Здесь можно открыть фрагменты или выполнять другие действия
                    true
                }
                else -> false
            }
        }
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

    // Фильтры
    private fun showFilterSearchSpecialistFragment() {
        binding.iconFilter.setOnClickListener {
            launchFilterSearchSpecialistFragment()
        }
    }

    private fun launchFilterSearchSpecialistFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, FilterSearchSpecialistFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun fetchSpecialists() {
        val city = filterData?.getString("cityUser")
        val spec = filterData?.getString("specUser")
        val age = filterData?.getString("ageUser")
        val ageExperience = filterData?.getString("ageExperienceUserUser")
        val chartExperience = filterData?.getString("chartExperienceUser")
        val typeExperience = filterData?.getString("typeExperienceUser")
        val hasHigherEducation = filterData?.getBoolean("hasHigherEducationUser")

        lifecycleScope.launch {
            val response = ApiFactory.apiService.searchUsers(
                city = city
            )
        //  val response = ApiFactory.apiService.getAllUsers()
            if (response.isSuccessful) {
                val specialists = response.body() ?: emptyList()
                binding.rvSpecList.layoutManager = LinearLayoutManager(context)
                binding.rvSpecList.adapter = SpecialistsAdapter(specialists)
            }
        }
    }


        private fun fetchVacancies() {
            val title = filterData?.getString("title")
            val city = filterData?.getString("city")
            val spec = filterData?.getString("spec")
            val ageExperience = filterData?.getString("ageExperience")
            val chartExperience = filterData?.getString("chartExperience")
            val typeExperience = filterData?.getString("typeExperience")
            val hasCompany = filterData?.getBoolean("hasCompany")
            val hasHigherEducation = filterData?.getBoolean("hasHigherEducation")
            lifecycleScope.launch {
               val response = ApiFactory.apiService.searchVacancies(false, "", "", "Москва", spec, "", "", true, "")
              //  val response = ApiFactory.apiService.getAllVacancies()
                if (response.isSuccessful) {
                    val vacancies = response.body() ?: emptyList()
                    binding.rvSpecList.layoutManager = LinearLayoutManager(context)
                    binding.rvSpecList.adapter = VacancyAdapter(vacancies)
                }
            }
        }



    companion object {
        private const val FRAGMENT_ERROR = "ListITSpecialistsFragment is null"
        fun newInstance(): Fragment {
            return ListITSpecialistsFragment()
        }
    }
}