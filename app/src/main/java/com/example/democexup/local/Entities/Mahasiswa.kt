package com.example.democexup.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Mahasiswa(
    @PrimaryKey(autoGenerate = false)
    val nim:String,
    val nama:String,
    val matakuliah_id : Int
)
