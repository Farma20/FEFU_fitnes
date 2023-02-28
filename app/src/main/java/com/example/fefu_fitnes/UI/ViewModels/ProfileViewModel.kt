package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.UserDataModel

class ProfileViewModel:ViewModel() {
    var currentUser = UserDataModel(
        "Райан", "Гослинг", "№583057349", "0 занятий"
    )
}