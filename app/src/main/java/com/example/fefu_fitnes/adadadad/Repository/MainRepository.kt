package com.example.fefu_fitnes.dadadada.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fefu_fitnes.UI.Models.*
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes.adadadad.WebDataSource.FefuFitRetrofit
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

object MainRepository: ViewModel() {

    private val currentUser = MutableLiveData<UserDataModel>()
    private val currentNews = MutableLiveData<List<NewsDataModel>>()
    private val currentUserEvents = MutableLiveData<List<BookingDataModel>>()
    private val currentEvent = MutableLiveData<UpdateEventDataModel>()
    private val allServices = MutableLiveData<List<ServicesModel>>()
    private val allEvent = MutableLiveData<List<UpdateEventDataModel>>()

    //связь с API
    private val gson = Gson()
    fun getUserDataFromServer(): LiveData<UserDataModel> {
        val result = MutableLiveData<UserDataModel>()
        viewModelScope.launch {
            try {
                val listResult = FefuFitRetrofit.retrofitService.getUserData()

                result.postValue(gson.fromJson(listResult, UserDataModel::class.java))
            }catch (e:Exception){
                println(e)
                result.postValue(UserDataModel("Юра", "Гослинг", "№583057349", "0 занятий"))
            }
        }

        return result
    }


    fun getAllEventFromServer():LiveData<Array<EventDataModel>>{

        val result = MutableLiveData<Array<EventDataModel>>()
        viewModelScope.launch {
            try {
                val listResult = FefuFitRetrofit.retrofitService.getAllEvents()
                result.postValue(gson.fromJson(listResult, Array<EventDataModel>::class.java))
            }catch (e:Exception){
                println("MainRepository нет соединения с сервером!!!!")
            }
        }
        return result
    }


    fun getUserEventsFromSever():MutableLiveData<List<BookingDataModel>>{
        val result = MutableLiveData<List<BookingDataModel>>()
        viewModelScope.launch {
            try {
                result.postValue(FefuFitRetrofit.retrofitService.getUserEvents())
            }catch (e:Exception){
                println(e)
            }
        }
        return result
    }

    fun pushNewBookingOnServer(eventId: Int){
        viewModelScope.launch {
            try {
                FefuFitRetrofit.retrofitService.newBooking(NewBookingDataModel(eventId))
            }catch (e:Exception){
                println(e)
            }
        }
    }



    //геттеры
    fun getUser(): LiveData<UserDataModel> {
        return currentUser
    }

    fun getNews(): LiveData<List<NewsDataModel>> {
        return currentNews
    }

    fun getEvent(): LiveData<UpdateEventDataModel> {
        return currentEvent
    }

    fun getEvents():LiveData<List<UpdateEventDataModel>>{
        return allEvent
    }

    fun getServices():LiveData<List<ServicesModel>>{
        return allServices
    }

    //Сеттеры
    fun setUser(user:UserDataModel){
        currentUser.value = user
    }

    fun setAllEvents(workouts: List<UpdateEventDataModel>){
        allEvent.value = workouts
    }

    fun setEvent(workout:UpdateEventDataModel){
        currentEvent.value = workout
    }

    fun setNews(events:List<NewsDataModel>){
        currentNews.value = events
    }

    fun setServices(services:List<ServicesModel>){
        allServices.value = services
    }


    init {

        currentUserEvents.value = listOf(BookingDataModel())

        currentUser.value = UserDataModel("Райан", "Гослинг", "№583057349", "0 занятий")
        currentNews.value = listOf(
            NewsDataModel("Чемпионат АССК по настольному теннису"),
            NewsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            NewsDataModel("Чем заняться в свободное время на каникулах?"),
            NewsDataModel("Чемпионат АССК по настольному теннису"),
            NewsDataModel("III этап зимнего сезона Студенческой Гребной Лиги"),
            NewsDataModel("Чем заняться в свободное время на каникулах?")
        )
        currentEvent.value = UpdateEventDataModel()


        allServices.value = listOf(
            ServicesModel("Студентам и сотрудникам", "— Разовое посещение", "300"),
            ServicesModel("Студентам и сотрудникам", "— 3 посещения", "800"),
            ServicesModel("Студентам и сотрудникам", "— 5 посещений", "1200"),
            ServicesModel("Гостям кампуса", "— 3 посещения", "900"),
            ServicesModel("Гостям кампуса", "— 5 посещений", "1400"),
        )


    }

}