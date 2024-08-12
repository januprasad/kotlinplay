package com.example.koin_test.di

import android.content.Context
import com.example.koin_test.PostsViewModel
import com.example.koin_test.SettingsRepository
import com.example.koin_test.SettingsViewModel
import com.example.koin_test.repo.PostsRepository
import com.example.koin_test.repo.PostsRepositoryImpl
import com.example.koin_test.retrofit.PostsApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun koinAppModule() =
    // we need context why bcz we try to bring sharedpref
    module {
        single {
            Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(PostsApi.BASE_URL)
                .build()
                .create(PostsApi::class.java)
        }
        single<PostsRepository>(named("PostsRepositoryImpl")) { PostsRepositoryImpl(get()) }
        single { SettingsRepository(get()) }
        single { androidContext().getSharedPreferences("shared-preferences-delegates-example", Context.MODE_PRIVATE) }
        viewModel { SettingsViewModel(get()) }
        viewModel { PostsViewModel(get(named("PostsRepositoryImpl"))) }
    }
