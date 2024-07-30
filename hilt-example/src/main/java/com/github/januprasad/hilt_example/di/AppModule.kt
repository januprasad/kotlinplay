package com.github.januprasad.hilt_example.di

import com.github.januprasad.hilt_example.repo.QuoteRepository
import com.github.januprasad.hilt_example.repo.QuotesRepositoryImpl
import com.github.januprasad.hilt_example.retrofit.QuotesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesQuotesAPI(): QuotesAPI {
        val logging =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val client =
            OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

        return Retrofit
            .Builder()
            .client(client)
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesAPI::class.java)
    }

    @Provides
    fun providesQuotesRepo(api: QuotesAPI): QuoteRepository = QuotesRepositoryImpl(api)
}
