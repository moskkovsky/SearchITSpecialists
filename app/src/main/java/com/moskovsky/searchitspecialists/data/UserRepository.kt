package com.moskovsky.searchitspecialists.data

import com.moskovsky.searchitspecialists.domain.User
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {
    suspend fun saveUser(user: User): Response<Unit> {
        return apiService.saveUser(user)
    }
}