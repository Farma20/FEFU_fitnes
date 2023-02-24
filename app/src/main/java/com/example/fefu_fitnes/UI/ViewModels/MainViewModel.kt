package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.Data.FitnessApi
import com.example.fefu_fitnes.Data.RegistrationBody
import com.example.fefu_fitnes.R
import com.example.fefu_fitnes.UI.Models.EventsDataModel
import com.example.fefu_fitnes.UI.Models.ServicesModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class MainViewModel:ViewModel(){

    private val gson = Gson()
    fun getUserData():LiveData<UserDataModel>{

        val result = MutableLiveData<UserDataModel>()
        viewModelScope.launch {
            val listResult = FitnessApi.retrofitService.getPhotos()
            result.postValue(gson.fromJson(listResult, UserDataModel::class.java))
        }
        return result
    }

    fun getWorkout():LiveData<Array<WorkoutDataModel>>{

        val result = MutableLiveData<Array<WorkoutDataModel>>()
        viewModelScope.launch {
            val listResult = FitnessApi.retrofitService.getWorkout()
            result.postValue(gson.fromJson(listResult, Array<WorkoutDataModel>::class.java))
        }
        return result
    }

    fun sendNewBooking(eventId: Int, userId: Int){

        viewModelScope.launch {
            var requestBody = RegistrationBody(event_id = 1)
            val listResult = FitnessApi.retrofitService.registerUser(requestBody)

        }


    }

    fun postMessage( id: Int){

            val JSON = "application/json; charset=utf-8".toMediaType()

            val data = mapOf<String, Int>("event_id" to id)


            val client = OkHttpClient()
            val body: RequestBody = JSONObject(data).toString().toRequestBody(JSON)
            val request = Request.Builder().url("http://172.20.10.2/api/booking/addNewBooking").post(body).build()

            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw IOException("Запрос к серверу не был успешен:" +
                                " ${response.code} ${response.message}")
                    }
                    println(response.body!!.string())
                }
            } catch (e: IOException) {
                println("Ошибка подключения: $e")
            }

    }



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

    var nearWorkout = WorkoutDataModel()

//    var nearWorkout = WorkoutDataModel(
//        0,
//        "Групповое занятие по аэробике",
//        "14:00 - 16:00",
//        "Корпус S, зал аэробики",
//        "Пердун Юлия Олеговна",
//        "Вы записаны",
//        "3/25",
//        "8 (999) 618 10",
//        "96kerdun.iuol@dvfu.ru",
//        "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
//    )






    var allWorkout: List<WorkoutDataModel> = listOf(
        WorkoutDataModel(
            1,
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",
        ),
        WorkoutDataModel(
            3,
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",

        ),
        WorkoutDataModel(
            4,
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",

        ),
        WorkoutDataModel(
            5,
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",

        ),
        WorkoutDataModel(
            6,
            "Групповое занятие по аэробике",
            "14:00 - 16:00",
            "Корпус S, зал аэробики",
            "Пердун Юлия Олеговна",
            "Оплачено",
            "3/25",
            "8 (999) 618 10",
            "96kerdun.iuol@dvfu.ru",
            "Степ аэробика – это специализированный тренинг, который идеально подходит для похудения, проработки мышц ног и ягодиц. Занятие на степ платформе состоит из набора базовых шагов. Они объединены в комбинации и выполняются в разных вариациях, которые отличаются по типу сложности. За счет изменения высоты шага уменьшается или увеличивается нагрузка.",

        )
    )

    var allServices: List<ServicesModel> = listOf(
        ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
        ServicesModel("Студентам и сотрудникам", "— 3 посещения", "800"),
        ServicesModel("Студентам и сотрудникам", "— 5 посещений", "1200"),
        ServicesModel("Гостям кампуса", "— 5 посещений", "1500"),
    )

}