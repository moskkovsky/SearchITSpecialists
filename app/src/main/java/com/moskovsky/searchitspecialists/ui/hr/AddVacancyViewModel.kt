package com.moskovsky.searchitspecialists.ui.hr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moskovsky.searchitspecialists.data.ApiFactory
import com.moskovsky.searchitspecialists.domain.Vacancy
import kotlinx.coroutines.launch

class AddVacancyViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun saveVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            try {
                val response = ApiFactory.apiService.saveVacancy(vacancy)
                if (response.isSuccessful) {
                    _status.value = "Vacancy saved successfully"
                } else {
                    _status.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _status.value = "Exception: ${e.message}"
            }
        }
    }
}