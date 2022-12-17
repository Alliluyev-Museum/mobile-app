package com.fitpteam.alliluyevmuseum.models

import com.google.gson.annotations.SerializedName

data class ExhibitsJson(
    @SerializedName("exhibits") val exhibits: List<Exhibit>
)
