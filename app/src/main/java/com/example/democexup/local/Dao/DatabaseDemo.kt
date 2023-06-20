package com.example.democexup.local.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.Matakuliah

@Database(entities = [Matakuliah::class, Dosen::class,Mahasiswa::class], version = 1)
abstract class DatabaseDemo : RoomDatabase() {

    abstract fun MatakuliahDao() : MatakuliahDao

    companion object {
        private var INSTANCE: DatabaseDemo? = null

        fun getInstance(context: Context): DatabaseDemo? {
            if (INSTANCE == null) {
                synchronized(DatabaseDemo::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseDemo::class.java, "Cexup.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}