package com.example.koin_test.retrofit

import com.example.koin_test.data.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {
    @GET("/posts")
    suspend fun getPosts(): Response<PostResponse>

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}
