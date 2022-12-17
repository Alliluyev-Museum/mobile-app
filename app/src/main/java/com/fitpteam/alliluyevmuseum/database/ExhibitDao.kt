package com.fitpteam.alliluyevmuseum.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.fitpteam.alliluyevmuseum.models.Exhibit

@Dao
interface ExhibitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exhibit: Exhibit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exhibits: List<Exhibit>)

    @Delete
    fun delete(exhibit: Exhibit)

    @Delete
    fun delete(exhibits: List<Exhibit>)

    @Query("SELECT * FROM exhibits WHERE roomId = :roomId")
    fun exhibitsByRoomId(roomId: Int): LiveData<List<Exhibit>>

    @Query("SELECT * FROM exhibits")
    fun allExhibits(): LiveData<List<Exhibit>>
}