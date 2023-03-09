package com.example.fefu_fitnes.UI.Register.ViewModels

import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Register.Models.UserEnterModel

class LoginViewModel:ViewModel() {

    private val userEnterData = UserEnterModel()

    //сетеры
    fun setUserEmail(email:String){
        userEnterData.userEmail = email
    }
    fun setUserPassword(password: String){
        userEnterData.userPassword = password
    }

    //геттеры
    fun getUserEmail():String{
        return userEnterData.userEmail
    }
    fun getUserPassword():String{
        return userEnterData.userPassword
    }
}