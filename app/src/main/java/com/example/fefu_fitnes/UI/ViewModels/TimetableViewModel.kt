package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.data.Repository.MainRepository

class TimetableViewModel:ViewModel() {

    private val allWorkout = MutableLiveData<List<WorkoutDataModel>>()

    //геттеры
    fun getWorkouts():LiveData<List<WorkoutDataModel>>{
        return allWorkout
    }

    //Передача выбранной тренировки
    fun setMainWorkout(workout:WorkoutDataModel){
        MainRepository.setWorkout(workout)
    }

    override fun onCleared() {
        super.onCleared()
    }


    init {

        val result = MainRepository.getAllWorkoutFromServer()
        result.observeForever{
            MainRepository.setAllWorkouts(it.toList())
        }

        MainRepository.getWorkouts().observeForever{
            allWorkout.value = it
        }

    }

}