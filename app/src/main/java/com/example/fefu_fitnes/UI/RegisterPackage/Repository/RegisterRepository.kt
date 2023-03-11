package com.example.fefu_fitnes.UI.RegisterPackage.Repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserRegisterModel

object RegisterRepository: ViewModel() {

    private val registerUserList = MutableLiveData<MutableList<UserRegisterModel>>()

    //сеттеры
    fun addNewUser(userData:UserRegisterModel?){
        if (userData != null) {
            registerUserList.value?.add(userData)
        }
    }

    //геттеры
    fun getUserList(): List<UserRegisterModel>?{
        return registerUserList.value
    }

}