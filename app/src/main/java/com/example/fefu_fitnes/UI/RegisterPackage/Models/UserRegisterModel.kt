package com.example.fefu_fitnes.UI.RegisterPackage.Models

import com.google.gson.annotations.SerializedName

data class UserRegisterModel(
    @SerializedName("name")
    var userLogin: String = "",
//    var userPhoneNumber: String = "",
    @SerializedName("email")
    var userEmail: String = "",
//    var userGender: String = "M",
//    var userBirthday:String ="",
    @SerializedName("pass")
    var userPassword:String = ""
)