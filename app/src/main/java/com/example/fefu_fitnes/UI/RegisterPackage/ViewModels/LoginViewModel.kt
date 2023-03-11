package com.example.fefu_fitnes.UI.RegisterPackage.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes.UI.RegisterPackage.Repository.RegisterRepository

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

    //методы связи с RegisterRepository
    fun validateLoginData():Boolean{
        val userList = RegisterRepository.getUserList()
        val loginEmail = getUserEmail()
        val loginPassword = getUserPassword()

        if (userList != null && loginEmail!=null && loginPassword!=null) {
            for (user in userList){
                if (user.userEmail == loginEmail && user.userPassword == loginPassword){
                    return true
                }
            }
        }

        return false
    }

    init {
        userEnterData.value = UserEnterModel()
    }
}