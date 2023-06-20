package com.example.democexup.local.Entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.Matakuliah

data class MatakuliahDenganMahasiswa(
    @Embedded val matakuliah: Matakuliah,
    @Relation(parentColumn = "id", entityColumn = "matakuliah_id")
    val mahasiswa: List<Mahasiswa>
)
