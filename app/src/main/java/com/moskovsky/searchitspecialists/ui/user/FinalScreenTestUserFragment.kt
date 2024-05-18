package com.moskovsky.searchitspecialists.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moskovsky.searchitspecialists.R
import com.moskovsky.searchitspecialists.databinding.FragmentFinalScreenTestUserBinding
import com.moskovsky.searchitspecialists.ui.app.ListITSpecialistsFragment

class FinalScreenTestUserFragment : Fragment() {

    private var _binding: FragmentFinalScreenTestUserBinding? = null
    private val binding: FragmentFinalScreenTestUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalScreenTestUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListITSpecialistsFragment()
    }

    // Главный экран
    private fun showListITSpecialistsFragment() {
        binding.btListItSpecialists.setOnClickListener {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "FinalScreenTestUserFragment is null"
        fun newInstance(): Fragment {
            return FinalScreenTestUserFragment()
        }
    }
}