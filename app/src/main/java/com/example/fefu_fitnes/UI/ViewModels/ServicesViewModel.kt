package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.ServicesModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository

class ServicesViewModel:ViewModel() {

    private val allServices = MutableLiveData<List<ServicesModel>>()

    //геттеры
    fun getServices():LiveData<List<ServicesModel>>{
        return allServices
    }

    override fun onCleared() {
        super.onCleared()
    }

    init {

        MainRepository.getServices().observeForever{
            allServices.value = it
        }
    }
}