package com.example.fefu_fitnes.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fefu_fitnes.UI.Models.BookingDataModel
import com.example.fefu_fitnes.UI.Models.EventDataModel
import com.example.fefu_fitnes.UI.Models.UpdateEventDataModel
import com.example.fefu_fitnes.UI.Models.WorkoutDataModel
import com.example.fefu_fitnes.dadadada.Repository.MainRepository

class TimetableViewModel:ViewModel() {

    private val allEvents = MutableLiveData<List<UpdateEventDataModel>>()
    private val userEvents = MutableLiveData<List<BookingDataModel>>()

    //геттеры
    fun getEvents():LiveData<List<UpdateEventDataModel>>{
        return allEvents
    }

    fun getUserEvents():LiveData<List<BookingDataModel>>{
        return userEvents
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
            val dateDay: String = if(startDateTime[0][startDateTime[0].length-2] != '0'){
                startDateTime[0].substring(startDateTime[0].length - 2)
            }else{
                startDateTime[0].substring(startDateTime[0].length - 2)[1].toString()
            }

            val uEventDataModel = UpdateEventDataModel(
                event.eventId,
                event.eventName,
                dateDay,
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
            println(dateDay)
        }
        return updateEventDataModels
    }


    init {

        userEvents.value = listOf(BookingDataModel())

        MainRepository.getUserEventsFromSever().observeForever{
            userEvents.value = it
        }

        val result = MainRepository.getAllEventFromServer()
        result.observeForever{
            MainRepository.setAllEvents(convertEvents(it.toList()))
        }

        MainRepository.getEvents().observeForever{
            allEvents.value = it
        }

    }

}