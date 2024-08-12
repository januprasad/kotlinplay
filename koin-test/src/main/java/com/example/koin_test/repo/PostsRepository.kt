package com.example.koin_test.repo

import com.example.koin_test.data.PostResponse
import com.example.koin_test.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(): Flow<NetworkResult<PostResponse>>
}
