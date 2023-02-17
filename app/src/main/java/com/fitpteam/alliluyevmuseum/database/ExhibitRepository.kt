package com.fitpteam.alliluyevmuseum.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.fitpteam.alliluyevmuseum.models.Exhibit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExhibitRepository(application: Application) {
    private val dbDao = ExhibitDatabase.getInstance(application).exhibitDao()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(exhibit: Exhibit) {
        coroutineScope.launch(Dispatchers.IO) {
            dbDao.insert(exhibit)
        }
    }

    fun insert(exhibits: List<Exhibit>) {
        coroutineScope.launch(Dispatchers.IO) {
            dbDao.insert(exhibits)
        }
    }

    fun delete(exhibit: Exhibit) {
        coroutineScope.launch(Dispatchers.IO) {
            dbDao.delete(exhibit)
        }
    }

    fun delete(exhibits: List<Exhibit>) {
        coroutineScope.launch(Dispatchers.IO) {
            dbDao.delete(exhibits)
        }
    }

    fun exhibitsByRoomId(roomId: Int): LiveData<List<Exhibit>> = dbDao.exhibitsByRoomId(roomId)

    fun allExhibits(): LiveData<List<Exhibit>> = dbDao.allExhibits()
}