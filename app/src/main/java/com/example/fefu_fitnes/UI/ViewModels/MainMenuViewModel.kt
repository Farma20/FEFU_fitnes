package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.EventsDataModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.data.Repository.MainRepository

class MainMenuViewModel:ViewModel() {
    private val currentUser = MutableLiveData<UserDataModel>()
    private val currentEvents = MutableLiveData<List<EventsDataModel>>()
    private val currentWorkout = MutableLiveData<WorkoutDataModel>()

    //геттеры
    fun getUser():LiveData<UserDataModel>{
        return currentUser
    }

    fun getEvents():LiveData<List<EventsDataModel>>{
        return currentEvents
    }

    fun getWorkout():LiveData<WorkoutDataModel>{
        return currentWorkout
    }

    init {
        val resultUserUpdate = MainRepository.getUserDataFromServer()
        resultUserUpdate.observeForever{
            MainRepository.setUser(it)
        }

        val resultEventsUpdate = MainRepository.getEventsFromServer()
        resultEventsUpdate.observeForever{
            MainRepository.setEvents(it.toList())
        }

        MainRepository.getUser().observeForever{
            currentUser.value = it
        }

        MainRepository.getEvents().observeForever{
            currentEvents.value = it
        }

        MainRepository.getWorkout().observeForever{
            currentWorkout.value = it
        }

    }

    override fun onCleared() {
        super.onCleared()
    }

}