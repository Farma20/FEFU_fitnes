package com.example.fefu_fitnes.UI.RegisterPackage.Repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserRegisterModel
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import kotlinx.coroutines.launch

object RegisterRepository: ViewModel() {

    private var userInit = MutableLiveData<Boolean>()

    private val registerUserList = MutableLiveData<MutableList<UserRegisterModel>>()


    fun pushLoginData(loginData: UserEnterModel){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.pushLoginData(loginData)
                setUserInit(true)
            }catch (e:Exception){
                println(e.message)
            }
        }
    }


    //сеттеры
    fun addNewUser(userData:UserRegisterModel?){
        if (userData != null) {
            registerUserList.value?.add(userData)
        }
    }

    fun setUserInit(bool:Boolean){
        userInit.value = bool
    }

    //геттеры
    fun getUserList(): List<UserRegisterModel>?{
        return registerUserList.value
    }
    fun getUserInit(): MutableLiveData<Boolean> {
        return userInit
    }

    init {
        registerUserList.value = mutableListOf(UserRegisterModel(
            "iiii",
            "+79024887366",
            "iurban.kustov.200@gmail.com",
            "М",
            "15.03.2000",
            "1u2u3u4u"
        ))
        userInit.value = false
    }

}