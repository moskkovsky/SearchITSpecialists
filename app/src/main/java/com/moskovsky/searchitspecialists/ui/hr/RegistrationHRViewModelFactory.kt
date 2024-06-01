package com.moskovsky.searchitspecialists.ui.hr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moskovsky.searchitspecialists.data.UserRepository

class RegistrationHRViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationHRViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationHRViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}