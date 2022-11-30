package com.fitpteam.alliluyevmuseum.models

import androidx.compose.runtime.Immutable

@Immutable
data class Exhibit (
    val name: String,
    val description: String,
    val fullDescription: String,
)