package com.example.koin_test.repo

import com.example.koin_test.data.PostResponse
import com.example.koin_test.retrofit.PostsApi
import com.example.koin_test.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostsRepositoryImpl(
    val api: PostsApi,
) : PostsRepository {
    override fun getPosts() =
        flow<NetworkResult<PostResponse>> {
            emit(NetworkResult.Loading())
            val response = api.getPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResult.Success(response.body()))
                } ?: run {
                    emit(NetworkResult.Error(message = "Error occurred"))
                }
            }
        }.flowOn(Dispatchers.IO)
}
