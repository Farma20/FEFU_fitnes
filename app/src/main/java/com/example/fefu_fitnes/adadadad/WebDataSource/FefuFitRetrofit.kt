package com.example.fefu_fitnes.adadadad.WebDataSource

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


private const val BASE_URL = "http://172.20.10.4/"

//создание перехватчика для логирования данных запросов
var interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

//class ReceivedCookiesInterceptor : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        println("========== req ============")
//        println( chain.request().method);
//        println( chain.request().url);
//
//
//
//        println("========== res =============")
//        val originalResponse: Response = chain.proceed(chain.request())
//        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//            // ...
//        }
//        println(originalResponse.code)
//        println(originalResponse.headers("Set-Cookie"))
//        println(originalResponse.body)
//        return originalResponse
//    }
//
//    private var cookies : String? = null;
//
//}

private class SessionCookieJar : CookieJar {
    private var cookies: List<Cookie> = Collections.emptyList()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (url.encodedPath.endsWith("login")) {
            this.cookies = ArrayList(cookies)
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
         if (!url.encodedPath.endsWith("login") && !cookies.isEmpty())
            return cookies
        return Collections.emptyList()
    }
}

//передача перехватчика в клиент, который мы передаем ретрофиту
private val client = OkHttpClient.Builder().cookieJar(SessionCookieJar()).addInterceptor(interceptor).build()

//инициализация ретрофита
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)

    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object FefuFitRetrofit {
    val retrofitService:FefuFitAPI by lazy {
        retrofit.create(FefuFitAPI::class.java)
    }
}