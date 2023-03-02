package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.NewsDataModel
import com.example.fefu_fitnes.UI.Models.UpdateEventDataModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository

class MainMenuViewModel:ViewModel() {
    private val currentUser = MutableLiveData<UserDataModel>()
    private val currentNews = MutableLiveData<List<NewsDataModel>>()
    private val currentEvent = MutableLiveData<UpdateEventDataModel>()

    //геттеры
    fun getUser():LiveData<UserDataModel>{
        return currentUser
    }

    fun getNews():LiveData<List<NewsDataModel>>{
        return currentNews
    }

    fun getWorkout():LiveData<UpdateEventDataModel>{
        return currentEvent
    }

    init {
        val resultUserUpdate = MainRepository.getUserDataFromServer()
        resultUserUpdate.observeForever{
            MainRepository.setUser(it)
        }

        MainRepository.getUser().observeForever{
            currentUser.value = it
        }

        MainRepository.getNews().observeForever{
            currentNews.value = it
        }

        MainRepository.getEvent().observeForever{
            currentEvent.value = it
        }

    }

    override fun onCleared() {
        super.onCleared()
    }

}