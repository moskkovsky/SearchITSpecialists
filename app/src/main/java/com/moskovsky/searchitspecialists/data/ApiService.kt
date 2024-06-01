package com.moskovsky.searchitspecialists.data

import com.moskovsky.searchitspecialists.domain.FavoriteHR
import com.moskovsky.searchitspecialists.domain.User
import com.moskovsky.searchitspecialists.domain.Vacancy
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/user/save")
    suspend fun saveUser(@Body user: User): Response<Unit>

    @POST("/vacancy/save")
    suspend fun saveVacancy(@Body vacancy: Vacancy): Response<Unit>

    @GET("/vacancy/all")
    suspend fun getAllVacancies(): Response<List<Vacancy>>

    @GET("/user/get-all")
    suspend fun getAllUsers() : Response <List<User>>

    @GET("/favhr/get-all")
    fun getAllFavorites(): Call<List<FavoriteHR>>

    @POST("/favhr/save")
    fun saveFavorite(@Body favorite: FavoriteHR): Call<Void>

    @GET("/user/get-by-specialist")
    suspend fun getUsersBySpecialist(@Query("specialist") specialist: String): List<User>
}