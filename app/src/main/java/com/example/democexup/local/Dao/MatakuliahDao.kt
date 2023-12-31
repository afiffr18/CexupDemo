package com.example.democexup.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.Matakuliah
import com.example.democexup.local.Entities.relations.MatakuliahDanDosen
import com.example.democexup.local.Entities.relations.MatakuliahDenganMahasiswa

@Dao
interface MatakuliahDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatakuliah(matakuliah: Matakuliah)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDosen(dosen: Dosen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    @Transaction
    @Query("select * from matakuliah")
    suspend fun getMatakuliahDanDosen() : List<MatakuliahDanDosen>

    @Transaction
    @Query("select * from matakuliah where id = :matakuliahId")
    suspend fun getMatakuliahDenganMahasiswa(matakuliahId : Int) : List<MatakuliahDenganMahasiswa>
}