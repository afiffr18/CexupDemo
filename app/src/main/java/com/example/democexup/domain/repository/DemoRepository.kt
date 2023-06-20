package com.example.democexup.domain.repository

import com.example.democexup.local.Dao.DatabaseDemo
import com.example.democexup.local.Dao.MatakuliahDao
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.Matakuliah
import com.example.democexup.local.Entities.relations.MatakuliahDanDosen
import com.example.democexup.local.Entities.relations.MatakuliahDenganMahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DemoRepository(private val mDb: DatabaseDemo) {
    private val matakuliahDao = mDb.MatakuliahDao()

    suspend fun getMatakuliah(): List<MatakuliahDanDosen> {
        return matakuliahDao.getMatakuliahDanDosen()
    }

    suspend fun getMahasiswa(matakulliahId : Int) : List<MatakuliahDenganMahasiswa>{
        return matakuliahDao.getMatakuliahDenganMahasiswa(matakulliahId)
    }
    suspend fun insertMatakuliah(matakuliah: Matakuliah) = withContext(Dispatchers.IO){
        mDb.MatakuliahDao().insertMatakuliah(matakuliah)
    }
    suspend fun insertDosen(dosen: Dosen) = withContext(Dispatchers.IO){
        mDb.MatakuliahDao().insertDosen(dosen)
    }

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa) = withContext(Dispatchers.IO){
        mDb.MatakuliahDao().insertMahasiswa(mahasiswa)
    }

}