package com.example.practice3.news.data.model

import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {
    @GET("/")
    suspend fun getFilms(): FilmsListResponse

//    companion object {
//        private const val CREATE_TIME_KEY = "createTime desc"
//    }
}