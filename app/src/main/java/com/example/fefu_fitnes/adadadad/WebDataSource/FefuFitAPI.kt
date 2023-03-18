package com.example.fefu_fitnes.adadadad.WebDataSource

import com.example.fefu_fitnes.UI.Models.BookingDataModel
import com.example.fefu_fitnes.UI.Models.NewBookingDataModel
import com.example.fefu_fitnes.UI.Models.UserDataModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserEnterModel
import com.example.fefu_fitnes.UI.RegisterPackage.Models.UserRegisterModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FefuFitAPI {
    @GET("/api/user/getAllUserInfo")
    suspend fun getUserData():String

    @GET("/api/event/getAllEvent")
    suspend fun getAllEvents():String

    @GET("/api/booking/getAllBooking")
    suspend fun getUserEvents():List<BookingDataModel>

    @POST("/api/user/login")
    suspend fun pushLoginData(@Body userEnterData:UserEnterModel):Map<String, String>

    @POST("/api/user/signup")
    suspend fun registerUser(@Body registerData:UserRegisterModel)

    @POST("/api/booking/addNewBooking")
    suspend fun newBooking(@Body newBooking: NewBookingDataModel)
}