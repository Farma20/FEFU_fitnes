package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.ServicesModel

class ServicesViewModel:ViewModel() {

    var allServices: List<ServicesModel> = listOf(
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— 3 посещения", "800"),
        ServicesModel("Студентам и сотрудникам", "— 5 посещений", "1200"),
        ServicesModel("Гостям кампуса", "— 3 посещения", "900"),
        ServicesModel("Гостям кампуса", "— 5 посещений", "1400"),
    )
}