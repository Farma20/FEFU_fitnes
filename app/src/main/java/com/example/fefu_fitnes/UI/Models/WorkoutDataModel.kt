package com.example.fefu_fitnes.UI.Models

import java.util.Date

data class WorkoutDataModel(
    var workoutId: Int = 0,
    var workoutName: String = "",
    var workoutTime: String = "",
    var workoutLocation: String = "",
    var couchName:String ="",
    var paymentStatus: String = "",
    var freeSpaces:String = "",
    var couchPhone: String = "",
    var couchEmail: String = "",
    var workoutDescription: String = "",
)

