package com.example.fefu_fitnes.Data

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL = "http://172.20.10.2"

//начальная настройка ретрофит и кенвертора json в string
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


data class RegistrationBody( //Дата класс для передачи информации через POST запрос
    val event_id : Int = 0
)

interface FitnessApiService{  //интерфейс общения клиента-сервера
    @GET("/api/user/getAllUserInfo")
    suspend fun getPhotos():String

    @GET("/api/event/getAllEvent")
    suspend fun getWorkout():String

    @POST("/api/booking/addNewBooking")
    suspend fun registerUser(@Body registrationBody: RegistrationBody) : String

}

object FitnessApi{ //глобальный объект, создающий ретрофит
    val retrofitService:FitnessApiService by lazy {
        retrofit.create(FitnessApiService::class.java)
    }
}
