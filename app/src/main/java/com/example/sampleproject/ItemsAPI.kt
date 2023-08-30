package com.example.sampleproject

import retrofit2.Response
import retrofit2.http.GET

interface ItemsAPI {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}