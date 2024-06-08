package com.moskovsky.searchitspecialists.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moskovsky.searchitspecialists.data.UserRepository
import com.moskovsky.searchitspecialists.domain.User
import kotlinx.coroutines.launch
import retrofit2.Response


class RegistrationStackViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun saveUser(user: User, onResult: (Response<Unit>) -> Unit) {
        viewModelScope.launch {
            val response = userRepository.saveUser(user)
            onResult(response)
        }
    }
}