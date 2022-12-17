package com.fitpteam.alliluyevmuseum.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Immutable
@Entity(tableName = "exhibits")
data class Exhibit (
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("fullDescription") val fullDescription: String,
    @SerializedName("roomId") val roomId: Int = 0,
)