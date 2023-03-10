package com.example.fefu_fitnes.UI.RegisterPackage.Models

data class UserRegisterModel(
    var userLogin: String = "",
    var userPhoneNumber: String = "",
    var userEmail: String = "",
    var userGender: String = "M",
    var userBirthday:String ="",
    var userPassword:String = ""
)