package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository

class ProfileViewModel:ViewModel() {

    private val currentUser = MutableLiveData<UserDataModel>()

    //геттеры
    fun getUser():LiveData<UserDataModel>{
        return currentUser
    }

    override fun onCleared() {
        super.onCleared()
    }

    init {
        MainRepository.getUser().observeForever{
            currentUser.value = it
        }
    }
}