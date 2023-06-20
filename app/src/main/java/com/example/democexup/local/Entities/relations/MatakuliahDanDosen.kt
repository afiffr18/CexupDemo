package com.example.democexup.local.Entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.Matakuliah

data class MatakuliahDanDosen(
    @Embedded val matakuliah: Matakuliah,

    @Relation(
        parentColumn = "id",
        entityColumn = "matakuliah_id"
    )
    val dosen: Dosen
)