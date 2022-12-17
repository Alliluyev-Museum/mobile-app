package com.fitpteam.alliluyevmuseum.ui.viewmodels

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.fitpteam.alliluyevmuseum.R
import com.fitpteam.alliluyevmuseum.database.ExhibitRepository
import com.fitpteam.alliluyevmuseum.models.ExhibitsJson
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader


class MainViewModel(private val repository: ExhibitRepository,
                    private val resources: Resources) : ViewModel() {

    fun uploadList() {
        val exhibitsData = repository.allExhibits()
        val reader = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.exhibits)))
        val newExhibits = Gson().fromJson(reader, ExhibitsJson::class.java).exhibits
        exhibitsData.observeForever { oldExhibits ->
            if (newExhibits.any { !oldExhibits.contains(it) })
                repository.insert(newExhibits)
            for (exhibit in oldExhibits.filterNot { newExhibits.contains(it) })
                repository.delete(exhibit)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                return MainViewModel(
                    ExhibitRepository(application),
                    application.resources
                ) as T
            }
        }
    }
}