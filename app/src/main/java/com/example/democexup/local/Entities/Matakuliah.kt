package com.example.democexup.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Matakuliah(
    @PrimaryKey val id : Int,
    val nama : String,
)
