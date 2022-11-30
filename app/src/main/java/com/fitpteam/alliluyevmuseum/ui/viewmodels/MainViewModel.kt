package com.fitpteam.alliluyevmuseum.ui.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fitpteam.alliluyevmuseum.models.Room

class MainViewModel : ViewModel() {
    val roomsList = mutableStateOf(listOf(
        Room("Комната 1"),
        Room("Комната 2"),
        Room("Комната 3"),
        Room("Комната 4"),
    ))
}