package com.fitpteam.alliluyevmuseum.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fitpteam.alliluyevmuseum.models.Exhibit

@Database(entities = [Exhibit::class], version = 1)
abstract class ExhibitDatabase : RoomDatabase() {
    abstract fun exhibitDao(): ExhibitDao

    companion object {
        @Volatile
        private var INSTANCE: ExhibitDatabase? = null

        fun getInstance(context: Context): ExhibitDatabase {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context,
                    ExhibitDatabase::class.java, "ExhibitData"
                ).build()
            return INSTANCE!!
        }
    }
}