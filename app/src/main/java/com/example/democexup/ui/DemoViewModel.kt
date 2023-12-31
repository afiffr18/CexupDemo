package com.example.democexup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.and2t2.secondhand.common.Resource

import com.example.democexup.domain.repository.DemoRepository
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.Matakuliah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class DemoViewModel(private val demoRepository: DemoRepository) : ViewModel() {

    fun getAlldata() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try{
            emit(Resource.success(demoRepository.getMatakuliah()))
        }catch (ex : IOException){
            emit(Resource.error(null,ex.message.toString()))
        }catch (ex : HttpException){
            emit(Resource.error(null,ex.message.toString()))
        }catch (ex : Exception){
            emit(Resource.error(null,ex.message.toString()))
        }
    }

    fun getAllMahasiswa(id:Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try{
            emit(Resource.success(demoRepository.getMahasiswa(id)))
        }catch (ex : IOException){
            emit(Resource.error(null,ex.message.toString()))
        }catch (ex : HttpException){
            emit(Resource.error(null,ex.message.toString()))
        }catch (ex : Exception){
            emit(Resource.error(null,ex.message.toString()))
        }
    }
    fun insertMatakuliah(matakuliah: Matakuliah){
        viewModelScope.launch {
            demoRepository.insertMatakuliah(matakuliah)
        }
    }

    fun insertDosen(dosen: Dosen){
        viewModelScope.launch {
            demoRepository.insertDosen(dosen)
        }
    }

    fun insertMahasiswa(mahasiswa : Mahasiswa){
        viewModelScope.launch {
            demoRepository.insertMahasiswa(mahasiswa)
        }
    }


}