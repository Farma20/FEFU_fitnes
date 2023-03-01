package com.example.fefu_fitnes.data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.Data.FitnessApi
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

class MainRepository {

    private val currentUser = MutableLiveData<UserDataModel>()
    private val currentEvents = MutableLiveData<List<EventsDataModel>>()
    private val currentWorkout = MutableLiveData<WorkoutDataModel>()
    private val allServices = MutableLiveData<List<ServicesModel>>()
    private val allWorkout = MutableLiveData<List<WorkoutDataModel>>()

    //связь с API
    private val gson = Gson()

    suspend fun getAPIUserData():LiveData<UserDataModel>{

        val result = MutableLiveData<UserDataModel>()
        val listResult = FitnessApi.retrofitService.getPhotos()
        result.postValue(gson.fromJson(listResult, UserDataModel::class.java))

        return result
    }

    suspend fun getAPIWorkout():LiveData<Array<WorkoutDataModel>>{

        val result = MutableLiveData<Array<WorkoutDataModel>>()
        val listResult = FitnessApi.retrofitService.getWorkout()
        result.postValue(gson.fromJson(listResult, Array<WorkoutDataModel>::class.java))

        return result
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

    //геттеры
    fun getUser(): LiveData<UserDataModel> {
        return currentUser
    }

    fun getEvents(): LiveData<List<EventsDataModel>> {
        return currentEvents
    }

    fun getWorkout(): LiveData<WorkoutDataModel> {
        return currentWorkout
    }

    fun getWorkouts():LiveData<List<WorkoutDataModel>>{
        return allWorkout
    }

    fun getServices():LiveData<List<ServicesModel>>{
        return allServices
    }

    //Сеттеры
    fun setWorkout(workout:WorkoutDataModel){
        currentWorkout.value = workout
    }

    companion object{

        private val instance = MainRepository()

        fun getInstance(): MainRepository{
            return instance
        }
    }

    init {
        currentUser.value = UserDataModel("Райан", "Гослинг", "№583057349", "0 занятий")
        currentEvents.value = listOf(
            EventsDataModel("Чемпионат АССК по настольному теннису"),
            EventsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            EventsDataModel("Чем заняться в свободное время на каникулах?"),
            EventsDataModel("Чемпионат АССК по настольному теннису"),
            EventsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            EventsDataModel("Чем заняться в свободное время на каникулах?")
        )
        currentWorkout.value = WorkoutDataModel()

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


        allServices.value = listOf(
            ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
            ServicesModel("Студентам и сотрудникам", "— 3 посещения", "800"),
            ServicesModel("Студентам и сотрудникам", "— 5 посещений", "1200"),
            ServicesModel("Гостям кампуса", "— 3 посещения", "900"),
            ServicesModel("Гостям кампуса", "— 5 посещений", "1400"),
        )

        allWorkout.value = listOf(
            WorkoutDataModel(
                0,
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
                2,
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

                )
        )
    }

}