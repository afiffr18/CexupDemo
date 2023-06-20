package com.example.democexup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.and2t2.secondhand.common.Resource

import com.example.democexup.domain.repository.DemoRepository
import kotlinx.coroutines.Dispatchers
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



}