package com.moskovsky.searchitspecialists.domain

data class Vacancy(
    val company: Boolean,
    val title: String,
    val description: String,
    val city: String,
    val specialist: String,
    val experience: String,
    val employment_type: String,
    val hasHigherEducation: Boolean,
    val workSchedule: String
)