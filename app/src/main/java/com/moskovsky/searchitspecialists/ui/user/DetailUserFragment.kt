package com.moskovsky.searchitspecialists.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.moskovsky.searchitspecialists.data.ApiFactory.apiService
import com.moskovsky.searchitspecialists.databinding.FragmentDetailUserBinding
import com.moskovsky.searchitspecialists.domain.FavoriteHR
import com.moskovsky.searchitspecialists.domain.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailUserFragment : Fragment() {
    private var _binding: FragmentDetailUserBinding? = null
    private val binding: FragmentDetailUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getSerializable("user") as? User
        if (user != null) {
            binding.tvItemName.text = user.specialist
            binding.tvSpecNameInfo.text = user.name
            binding.tvSpecSurnameInfo.text = user.surname
            binding.tvSpecCityInfo.text = user.city
            binding.tvSpecTgInfo.text = user.telegram
            binding.tvSpecDescriptionInfo.text = user.experienceDescription
            binding.tvSpecEducInfo.text = user.education
            binding.tvSpecExpAgeInfo.text = user.experience
            binding.tvSpecOfficeInfo.text = user.employmentType
            binding.tvSpecFullExpInfo.text = user.workSchedule

            binding.ivLogoShare.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Смотри какой кандидат!: ${binding.tvItemName.text}, ${binding.tvSpecNameInfo.text}, ${binding.tvSpecSurnameInfo.text}, ${binding.tvSpecCityInfo.text}, ${binding.tvSpecTgInfo.text}, ${binding.tvSpecDescriptionInfo.text}, ${binding.tvSpecEducInfo.text}, ${binding.tvSpecExpAgeInfo.text}, ${binding.tvSpecOfficeInfo.text}, ${binding.tvSpecFullExpInfo.text}"
                    )
                }
                val chooser = Intent.createChooser(shareIntent, "Поделиться через")
                if (shareIntent.resolveActivity(requireContext().packageManager) != null) {
                    startActivity(chooser)
                } else {
                    Toast.makeText(context, "Нет приложений для поделиться", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            binding.ivLogoLike.setOnClickListener {
                saveFavorite(user)
            }
        }

    }

    private fun saveFavorite(user: User) {
        Log.d("DetailUserFragment", "saveFavorite called with user: $user")
        val favorite = FavoriteHR(
            name = user.name,
            surname = user.surname,
            specialist =  user.specialist,
            city =  user.city,
            telegram = user.telegram,
            experienceDescription =  user.experienceDescription,
            age = user.age,
            experience = user.experience,
            education = user.education,
            hasHigherEducation = user.hasHigherEducation,
            workSchedule = user.workSchedule,
            employmentType = user.employmentType,
            selectedTechnologies = user.selectedTechnologies
        )

        val call = apiService.saveFavorite(favorite)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("DetailUserFragment", "onResponse called with response: $response")
                if (response.isSuccessful) {
                    Toast.makeText(context, "Специалист добавлен в избранное", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("DetailUserFragment", "Error response: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Специалист не добавлен в избранное", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("DetailUserFragment", "onFailure called with error: ${t.message}", t)
                Toast.makeText(context, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        private const val FRAGMENT_ERROR = "DetailUserFragment is null"
        fun newInstance(): Fragment {
            return DetailUserFragment()
        }
    }

}