package com.example.democexup.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dosen(
    @PrimaryKey(autoGenerate = false)
    val nid:String,
    val nama:String,
    val matakuliah_id : Int
)
