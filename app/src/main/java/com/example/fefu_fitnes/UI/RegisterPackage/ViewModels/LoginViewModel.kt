package com.example.fefu_fitnes.UI.RegisterPackage.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel

class LoginViewModel:ViewModel() {
    private val userEnterData = MutableLiveData<UserEnterModel>()


    //сетеры
    fun setUserEmail(email:String){
        userEnterData.value?.userEmail = email
    }
    fun setUserPassword(password: String){
        userEnterData.value?.userPassword = password
    }

    //геттеры
    fun getUserEmail():String?{
        return userEnterData.value?.userEmail
    }
    fun getUserPassword():String?{
        return userEnterData.value?.userPassword
    }

    init {
        userEnterData.value = UserEnterModel()
    }
}