package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.EventsDataModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel

class MainMenuViewModel:ViewModel() {

    var currentUser: UserDataModel = UserDataModel(
        "Райан", "Гослинг", "№583057349", "0 занятий")

    var currentEvents: List<EventsDataModel> = listOf(
        EventsDataModel("Чемпионат АССК по настольному теннису"),
        EventsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
        EventsDataModel("Чем заняться в свободное время на каникулах?"),
        EventsDataModel("Чемпионат АССК по настольному теннису"),
        EventsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
        EventsDataModel("Чем заняться в свободное время на каникулах?")
    )

    var currentWorkout: WorkoutDataModel = WorkoutDataModel()

//    WorkoutDataModel(
//    0,
//    "Групповое занятие по аэробике",
//    "14:00 - 16:00",
//    "Корпус S, зал аэробики",
//    "Пердун Юлия Олеговна",
//    "Вы записаны",
//    "3/25",
//    "8 (999) 618 10",
//    "96kerdun.iuol@dvfu.ru",
//    "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
//    )
}