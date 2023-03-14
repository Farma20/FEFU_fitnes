package com.example.fefu_fitnes.UI.RegisterPackage.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserRegisterModel
import com.example.fefu_fitnes.UI.RegisterPackage.Repository.RegisterRepository

class RegisterViewModel: ViewModel() {
    private val userRegisterData = MutableLiveData<UserRegisterModel>()

    //Сеттеры
    fun setUserLogin(login:String){
        userRegisterData.value?.userLogin = login
    }
//    fun setUserPhoneNumber(phone:String){
//        userRegisterData.value?.userPhoneNumber = phone
//    }
    fun setUserEmail(email:String){
        userRegisterData.value?.userEmail = email
    }
//    fun setUserGender(gender:String){
//        userRegisterData.value?.userGender = gender
//    }
//    fun setUserBirthday(date:String){
//        userRegisterData.value?.userBirthday = date
//    }
    fun setUserPassword(password:String){
        userRegisterData.value?.userPassword = password
    }

    //геттеры
    fun getUserLogin():String?{
        return userRegisterData.value?.userLogin
    }
//    fun getUserPhoneNumber():String?{
//        return userRegisterData.value?.userPhoneNumber
//    }
    fun getUserEmail():String?{
        return userRegisterData.value?.userEmail
    }
//    fun getUserGender():String?{
//        return userRegisterData.value?.userGender
//    }
//    fun getUserBirthday():String?{
//        return userRegisterData.value?.userBirthday
//    }
    fun getUserPassword():String?{
        return userRegisterData.value?.userPassword
    }

    //методы связи с RegisterRepository
    fun pushUserData(){
        RegisterRepository.addNewUser(userRegisterData.value)
    }

    fun registerUser(){
        userRegisterData.value?.let { RegisterRepository.registerNewUser(it) }
    }

    init {
        userRegisterData.value = UserRegisterModel()
    }
}