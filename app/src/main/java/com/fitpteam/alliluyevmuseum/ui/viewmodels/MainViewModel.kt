package com.fitpteam.alliluyevmuseum.ui.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fitpteam.alliluyevmuseum.models.Room

class MainViewModel : ViewModel() {
    val roomsList = mutableStateOf(listOf(
        Room("Комната 1", Uri.parse("1")),
        Room("Комната 2", Uri.parse("1")),
        Room("Комната 3", Uri.parse("1")),
        Room("Комната 4", Uri.parse("1")),
        Room("Комната 5", Uri.parse("1"))
    ))
}