package com.example.democexup.domain.repository

import com.example.democexup.local.Dao.DatabaseDemo
import com.example.democexup.local.Dao.MatakuliahDao
import com.example.democexup.local.Entities.Matakuliah
import com.example.democexup.local.Entities.relations.MatakuliahDanDosen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DemoRepository(private val mDb: DatabaseDemo) {
    private val matakuliahDao = mDb.MatakuliahDao()

    suspend fun getMatakuliah(): List<MatakuliahDanDosen> {
        return matakuliahDao.getMatakuliahDanDosen()
    }

    suspend fun insertMatakuliah(matakuliah: Matakuliah) = withContext(Dispatchers.IO){
        mDb.MatakuliahDao().insertMatakuliah(matakuliah)
    }
}