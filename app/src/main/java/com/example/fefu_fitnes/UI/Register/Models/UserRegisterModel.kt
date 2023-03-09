package com.example.fefu_fitnes.UI.Register.Models

data class UserRegisterModel(
    var userLogin: String = "",
    var userPhoneNumber: String = "",
    var userEmail: String = "",
    var userGender: String = "",
    var userBirthday:String ="",
    var userPassword:String = ""
)