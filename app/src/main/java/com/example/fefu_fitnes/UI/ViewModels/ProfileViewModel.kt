package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.UserDataModel

class ProfileViewModel:ViewModel() {

    private val currentUser = MutableLiveData<UserDataModel>()

    //геттеры
    fun getUser():LiveData<UserDataModel>{
        return currentUser
    }

    init {
        currentUser.value = UserDataModel(
            "Райан", "Гослинг", "№583057349", "0 занятий"
        )
    }
}