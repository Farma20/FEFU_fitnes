package com.example.fefu_fitnes.UI.Models

data class EventDataModel(
    var eventId: Int = 0,
    var eventName: String = "",
    var beginTime:String = "",
    var endTime:String = "",
    var eventLocation: String = "",
    var couchName:String ="",
    var couchPhone: String = "",
    var couchEmail: String = "",
    var totalSpaces: Int = 0,
    var occupiedSpaces: Int = 0,
    var eventDescription: String = ""
)