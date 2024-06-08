package com.moskovsky.searchitspecialists.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moskovsky.searchitspecialists.data.UserRepository

class RegistrationStackViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationStackViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationStackViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}