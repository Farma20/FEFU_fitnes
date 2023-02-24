package com.example.fefu_fitnes.UI.Models

data class WorkoutDataModel(
    val workoutName: String = "",
    val workoutTime: String = "",
    val workoutLocation: String = "",
    val couchName:String ="",
    val paymentStatus: String = "",
    val freeSpaces:String = "",

    val couchPhone: String = "",
    val couchEmail: String = "",
    val workoutDescription: String = "",
    val workoutPhotos: List<Int> = listOf()
)