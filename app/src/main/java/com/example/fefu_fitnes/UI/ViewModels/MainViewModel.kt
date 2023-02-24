package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.EventsDataModel
import com.example.fefu_fitnes.UI.Models.ServicesModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel

class MainViewModel:ViewModel() {

    var user = UserDataModel(
        "Райан", "Гослинг", "№583057349", "0 занятий"
    )

    var events:List<EventsDataModel> = listOf(
        EventsDataModel("Чемпионат АССК по настольному теннису"),
        EventsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
        EventsDataModel("Чем заняться в свободное время на каникулах?"),
        EventsDataModel("Чемпионат АССК по настольному теннису"),
        EventsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
        EventsDataModel("Чем заняться в свободное время на каникулах?")
    )

    var nearWorkout = WorkoutDataModel(
        "Групповое занятие по аэробике",
        "14:00 - 16:00",
        "Корпус S, зал аэробики",
        "Пердун Юлия Олеговна",
        "Оплачено",
        "3/25",
        "8 (999) 618 10",
        "96kerdun.iuol@dvfu.ru",
        "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
        listOf(
            R.drawable.workout_photo_1,
            R.drawable.workout_photo_2,
            R.drawable.workout_photo_3,
            R.drawable.workout_photo_4,
            R.drawable.workout_photo_5
        )
    )






    var allWorkout: List<WorkoutDataModel> = listOf(
        WorkoutDataModel(
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
            listOf(
                R.drawable.workout_photo_1,
                R.drawable.workout_photo_2,
                R.drawable.workout_photo_3,
                R.drawable.workout_photo_4,
                R.drawable.workout_photo_5
            )
        ),
        WorkoutDataModel(
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
            listOf(
                R.drawable.workout_photo_1,
                R.drawable.workout_photo_2,
                R.drawable.workout_photo_3,
                R.drawable.workout_photo_4,
                R.drawable.workout_photo_5
            )
        ),
        WorkoutDataModel(
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
            listOf(
                R.drawable.workout_photo_1,
                R.drawable.workout_photo_2,
                R.drawable.workout_photo_3,
                R.drawable.workout_photo_4,
                R.drawable.workout_photo_5
            )
        ),
        WorkoutDataModel(
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
            listOf(
                R.drawable.workout_photo_1,
                R.drawable.workout_photo_2,
                R.drawable.workout_photo_3,
                R.drawable.workout_photo_4,
                R.drawable.workout_photo_5
            )
        ),
        WorkoutDataModel(
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
            listOf(
                R.drawable.workout_photo_1,
                R.drawable.workout_photo_2,
                R.drawable.workout_photo_3,
                R.drawable.workout_photo_4,
                R.drawable.workout_photo_5
            )
        )
    )

    var allServices: List<ServicesModel> = listOf(
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300")
    )

}