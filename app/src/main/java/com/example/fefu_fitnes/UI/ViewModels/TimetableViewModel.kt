package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.EventDataModel
import com.example.fefu_fitnes.UI.Models.UpdateEventDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository

class TimetableViewModel:ViewModel() {

    private val allEvents = MutableLiveData<List<UpdateEventDataModel>>()

    //геттеры
    fun getEvents():LiveData<List<UpdateEventDataModel>>{
        return allEvents
    }

    //Передача выбранной тренировки
    fun setMainEvent(workout:UpdateEventDataModel){
        MainRepository.setEvent(workout)
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun convertEvents(events: List<EventDataModel>):List<UpdateEventDataModel>{
        val updateEventDataModels = mutableListOf<UpdateEventDataModel>()
        for (event in events){

            val startDateTime:List<String> = event.beginTime.split("\\s".toRegex())
            val endDateTime: List<String> = event.endTime.split("\\s".toRegex())

            val uEventDataModel = UpdateEventDataModel(
                event.eventId,
                event.eventName,
                startDateTime[0],
                startDateTime[1].substring(0, startDateTime[1].length - 3),
                endDateTime[1].substring(0, endDateTime[1].length - 3),
                event.eventLocation,
                event.couchName,
                event.couchPhone,
                event.couchEmail,
                event.totalSpaces,
                event.occupiedSpaces,
                event.eventDescription
            )
            updateEventDataModels.add(uEventDataModel)
        }
        return updateEventDataModels
    }


    init {

        val result = MainRepository.getAllEventFromServer()
        result.observeForever{
            MainRepository.setAllEvents(convertEvents(it.toList()))
        }

        MainRepository.getEvents().observeForever{
            allEvents.value = it
        }

    }

}