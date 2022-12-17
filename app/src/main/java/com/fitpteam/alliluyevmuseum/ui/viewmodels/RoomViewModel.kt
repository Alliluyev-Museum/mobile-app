package com.fitpteam.alliluyevmuseum.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.fitpteam.alliluyevmuseum.database.ExhibitRepository
import com.fitpteam.alliluyevmuseum.models.Exhibit


class RoomViewModel(private val repository: ExhibitRepository) : ViewModel() {
    val exhibitList = mutableStateOf(listOf<Exhibit>())

    fun updateExhibitsList(roomId: Int) {
        repository.exhibitsByRoomId(roomId).observeForever {
            exhibitList.value = it
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

                return RoomViewModel(
                    ExhibitRepository(application)
                ) as T
            }
        }
    }
}