package com.example.democexup.local.Entities

import androidx.room.PrimaryKey

data class Mahasiswa(
    @PrimaryKey(autoGenerate = false)
    val nim:String,
    val nama:String,
    val matakuliah_id : Int
)
