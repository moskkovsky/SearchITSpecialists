package com.moskovsky.searchitspecialists.domain

import java.io.Serializable

data class FavoriteHR(
    val name: String,
    val surname: String,
    val telegram: String? = null,
    val age: String? = "",
    val experience: String? = "",
    val city: String? = "",
    val education: String? = "",
    val hasHigherEducation: Boolean? = false,
    val workSchedule: String? = "",
    val experienceDescription: String? = "",
    val employmentType: String? = "",
    val specialist: String? = "",
    val selectedTechnologies: List<String>? = listOf()
) : Serializable